package eu.telecomnancy.amio.polling;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Timer;

import eu.telecomnancy.amio.iotlab.entities.Mote;
import eu.telecomnancy.amio.persistence.IotLabDatabase;
import eu.telecomnancy.amio.persistence.IotLabDatabaseProvider;

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

        // Create the timer and plan the polling task every POLLING_DELAY ms
        _timer = new Timer();
        schedulePollingTask(0, Constants.Polling.POLLING_DELAY);

        Log.i(TAG, "Service started");

        // If the service get killed, after returning from here, restart it
        return START_STICKY;
    }

    /**
     * Given a list of motes, add all the ones not already registered in the database and their
     * associated values
     *
     * @param motes Received payload of motes
     */
    private void recordMotesInDatabase(List<Mote> motes) {
        // Register all the missing motes in the database
        motes.stream()
                // Filtering only the ones not existing in the database
                .filter(mote
                        -> !_database.moteDao().exists(mote.getName()))
                // Converting the provided mote to its database representation
                .map(mote
                        -> new eu.telecomnancy.amio.persistence.models.Mote(mote.getName()))
                // Creating the new record
                .forEach(mote -> {
                    mote.moteId = (int) _database.moteDao().insert(mote);
                    Log.d(TAG, "New mote added in the database: " + mote);
                });

        // TODO: register their associated data
    }

    /**
     * Schedule the polling task
     *
     * @param delay Delay in milliseconds before task is to be executed
     * @param period Time in milliseconds between successive task executions
     */
    @SuppressWarnings("SameParameterValue")
    private void schedulePollingTask(long delay, long period) {
        // Create a new task and define its callback in order to access the data it may provide
        // as parameter
        _pollingTask = new PollingTaskBase() {
            @Override
            public void callback(List<Mote> motes) {
                // Log the received payload
                Log.i(TAG, motes.size() + " mote(s) received");

                motes.forEach(mote
                        -> Log.d(TAG, mote.toString()));

                // Store the received motes
                recordMotesInDatabase(motes);
            }
        };

        _timer.scheduleAtFixedRate(_pollingTask, delay, period);

        Log.i(TAG, String.format("Task schedule for %d ms, every %d ms", delay, period));
    }

}
