package eu.telecomnancy.amio.iotlab;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.TimerTask;

import eu.telecomnancy.amio.iotlab.entities.MoteCollection;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Custom task to be executed to poll the iot lab's server
 */
public abstract class PollingTaskBase extends TimerTask {

    /**
     * Inner HttpClient used for HTTP requests
     */
    private final OkHttpClient _httpClient = new OkHttpClient();

    /**
     * Primary link to the API
     */
    private final Request iotLabPrimary = new Request.Builder()
            .url("http://iotlab.telecomnancy.eu/rest/data/1/light1/last")
            .build();

    /**
     * Secondary link to the API in case of failure of the first one (fallback)
     */
    private final Request iotLabSecondary = new Request.Builder()
            .url("https://gist.githubusercontent.com/pBouillon/c07f123dc466cb45f198bcebf072ddfb/" +
                    "raw/ddf75605ea49f024b2bf3fbdcea52faf9f730454/iotlab-light1-1-last")
            .build();

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
    public abstract void callback(MoteCollection motes);

    @Override
    public void run() {
        Log.i(TAG, "Polling task triggered");

        MoteCollection motes = new MoteCollection();

        try {
            Log.d(TAG, "Sending request to " + iotLabSecondary.url());

            // Send the HTTP GET request to the API
            Response response = _httpClient
                    .newCall(iotLabSecondary)
                    .execute();

            // Retrieve the payload of the request
            ResponseBody payload = response.body();

            if (payload != null) {
                // If any data is fetched, deserialize it into the appropriate object
                motes = new Gson().fromJson(payload.string(), MoteCollection.class);
            } else {
                Log.w(TAG, "Unable to retrieve the body from the response");
            }

        } catch (IOException e) {
            // In case of failure, pass a nutshell to the callback
            Log.e(TAG, "Failed to perform the HTTP request", e);
        }

        // Call the used-defined callback
        // If no data were fetched / deserialized, `motes` will still be an initialized empty object
        callback(motes);

        Log.i(TAG, "Polling task successfully executed");
    }

}
