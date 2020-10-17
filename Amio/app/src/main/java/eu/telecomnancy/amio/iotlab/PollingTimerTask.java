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
    private static final String TAG = PollingTimerTask.class.getName();

    @Override
    public void run() {
        Log.i(TAG, "Polling task triggered");

        // Implement polling

        Log.i(TAG, "Polling task successfully executed");
    }

}
