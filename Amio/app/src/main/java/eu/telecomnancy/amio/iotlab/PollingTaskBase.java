package eu.telecomnancy.amio.iotlab;

import android.util.Log;

import java.util.TimerTask;

/**
 * Custom task to be executed to poll the iot lab's server
 */
public abstract class PollingTaskBase extends TimerTask {

    /**
     * Android logging tag for this class
     */
    private static final String TAG = PollingTaskBase.class.getSimpleName();

    /**
     * Define a custom callback method to be executed when the task has run its job
     *
     * This custom callback methods allows the caller to use all data provided as parameter in its
     * scope without exposing any property
     */
    public abstract void callback();

    @Override
    public void run() {
        Log.i(TAG, "Polling task triggered");

        // TODO: Implement polling
        Log.v(TAG, "Dummy polling");

        // Call the used-defined callback
        callback();

        Log.i(TAG, "Polling task successfully executed");
    }

}
