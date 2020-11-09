package eu.telecomnancy.amio.polling;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Timer;

import eu.telecomnancy.amio.iotlab.entities.Mote;

/**
 * Polling service who will periodically fetch data from the server
 */
public class PollingService extends Service {

    /**
     * Android logging tag for this class
     */
    private static final String TAG = PollingService.class.getName();

    /**
     * Inner-timer used for firing events
     */
    private Timer _timer;

    /**
     * TimerTask to be run by the timer
     */
    private PollingTaskBase _pollingTask;

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
                Log.d(TAG, motes.size() + " mote(s) received");
                motes.forEach(mote -> Log.d(TAG, mote.toString()));
            }
        };

        _timer.scheduleAtFixedRate(_pollingTask, delay, period);

        Log.i(TAG, String.format("Task schedule for %d ms, every %d ms", delay, period));
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

        // Create the timer and plan the polling task every POLLING_DELAY ms
        _timer = new Timer();
        schedulePollingTask(0, Constants.Polling.POLLING_DELAY);

        Log.i(TAG, "Service started");

        // If the service get killed, after returning from here, restart it
        return START_STICKY;
    }

}
