package eu.telecomnancy.amio.iotlab;

import android.util.Log;

import java.util.TimerTask;

/**
 * Custom task to be executed to poll the iot lab's server
 */
public class PollingTimerTask extends TimerTask {

    /**
     * Android logging tag for this class
     */
    private static final String TAG = PollingTimerTask.class.getSimpleName();

    @Override
    public void run() {
        Log.i(TAG, "Polling task triggered");

        // TODO: Implement polling
        Log.v(TAG, "Dummy polling");

        Log.i(TAG, "Polling task successfully executed");
    }

}
