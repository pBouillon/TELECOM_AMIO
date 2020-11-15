package eu.telecomnancy.amio.polling;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Timer;

import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.iotlab.models.Mote;
import eu.telecomnancy.amio.persistence.IotLabDatabase;
import eu.telecomnancy.amio.persistence.IotLabDatabaseProvider;
import eu.telecomnancy.amio.persistence.daos.MoteDao;
import eu.telecomnancy.amio.persistence.daos.RecordDao;
import eu.telecomnancy.amio.persistence.entities.Record;
import eu.telecomnancy.amio.polling.contexts.PollingContext;

/**
 * Polling service who will periodically fetch data from the server
 */
public class PollingService extends Service {

    /**
     * Android logging tag for this class
     */
    private static final String TAG = PollingService.class.getName();

    /**
     * Local database access layer
     */
    private IotLabDatabase _database;

    /**
     * TimerTask to be run by the timer
     */
    private PollingTaskBase _pollingTask;

    /**
     * Inner-timer used for firing events
     */
    private Timer _timer;

    /**
     * Create a new task and define its callback in order to access the data it may provide
     *  as parameter
     *
     * @return The newly created polling task
     */
    private PollingTaskBase createPollingTask() {
        PollingContext context = new PollingContext(getApplicationContext());

        // Create a new task and define its callback in order to access the data it may provide
        // as parameter
        return new PollingTaskBase(context) {
            @Override
            public void callback(List<Mote> motes) {
                // Log the received payload
                Log.i(TAG, motes.size() + " mote(s) received");

                motes.forEach(mote
                        -> Log.d(TAG, mote.toString()));


                // Broadcast the new data to the UI
                sendBroadcastMessage(motes);

                // Store the received motes
                storeRecords(motes);
            }
        };
    }

    /**
     * Initialize the notification channel to allow the task to further send push notifications
      */
    private void initializeNotificationChannel() {
        // Retrieve channel details
        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);

        // Set channel options
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        // Create the channel
        NotificationChannel channel = new NotificationChannel(
                eu.telecomnancy.amio.notification.Constants.Notification.CHANNEL_ID,
                name,
                importance);

        channel.setDescription(description);

        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        Log.i(TAG, "Notification channel initialized");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // No binding provided in this case
        return null;
    }

    @Override
    public void onDestroy() {
        // Cancel the running / scheduled task
        _pollingTask.cancel();

        // Cancel the timer
        _timer.cancel();

        // Remove all canceled tasks from the timer's task queue
        _timer.purge();

        super.onDestroy();

        Log.i(TAG, "Service destroyed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        // Initialize the database access
        _database = IotLabDatabaseProvider.getOrCreateInstance(getApplicationContext());

        // Initialize the notification channel
        initializeNotificationChannel();

        // Create the timer and plan the polling task every POLLING_DELAY ms
        _timer = new Timer();
        schedulePollingTask(0, Constants.Polling.POLLING_DELAY);

        Log.i(TAG, "Service started");

        // If the service get killed, after returning from here, restart it
        return START_STICKY;
    }

    /**
     * Add all motes not already tracked in the database
     *
     * @param motes The list of motes to track
     */
    private void storeNewMotes(List<Mote> motes) {
        MoteDao moteDao = _database.moteDao();

        // Register all the missing motes in the database
        motes.stream()
                // Filtering only the ones not existing in the database
                .filter(mote
                        -> !moteDao.exists(mote.getName()))
                // Converting the provided mote to its database representation
                .map(mote
                        -> new eu.telecomnancy.amio.persistence.entities.Mote(mote.getName()))
                // Storing the new motes
                .forEach(mote -> {
                    mote.moteId = moteDao.insert(mote);
                    Log.d(TAG, "New mote added in the database: " + mote);
                });
    }

    /**
     * Given a list of motes, add all the ones not already registered in the database and their
     * associated values
     *
     * @param motes Received payload of motes
     */
    private void storeRecords(List<Mote> motes) {
        MoteDao moteDao = _database.moteDao();
        RecordDao recordDao = _database.recordDao();

        // Register all the missing motes in the database
        storeNewMotes(motes);

        // Register the data retrieved
        motes.stream()
                // Creating the record from their value and name
                .map(mote -> {
                    long moteId = moteDao.getByName(mote.getName()).moteId;
                    return new Pair<>(moteId, mote);
                })
                // Storing the new records
                .forEach(pair -> {
                    // Create the record from the mote id and the data originally retrieved
                    Mote source = pair.second;
                    Record record = new Record(pair.first, source.getBrightness(),
                            source.getHumidity(), source.getBattery(), source.getTimestamp(),
                            source.getTemperature());

                    // Insert the newly created record
                    record.recordId = recordDao.insert(record);

                    Log.d(TAG, "New record added in the database: " + record);
                });
    }

    /**
     * Send a broadcast message that contain the mote list
     *
     * @param motes updated motes list
     */
    private void sendBroadcastMessage(List<Mote> motes) {
        Intent broadcastMessage = new Intent(Constants.Broadcast.UPDATED_DATA);

        Bundle moteBundle = new Bundle();
        moteBundle.putSerializable(Constants.Broadcast.IDENTIFIER, (Serializable) motes);
        broadcastMessage.putExtra(Constants.Broadcast.BUNDLE_IDENTIFIER, moteBundle);

        sendBroadcast(broadcastMessage);

        Log.d(TAG, "Broadcast message sent");
    }

    /**
     * Schedule the polling task
     *
     * @param delay Delay in milliseconds before task is to be executed
     * @param period Time in milliseconds between successive task executions
     */
    @SuppressWarnings("SameParameterValue")
    private void schedulePollingTask(long delay, long period) {
        _pollingTask = createPollingTask();

        _timer.scheduleAtFixedRate(_pollingTask, delay, period);

        Log.i(TAG, String.format("Task scheduled every %d ms, starting in %d ms", period, delay));
    }

}
