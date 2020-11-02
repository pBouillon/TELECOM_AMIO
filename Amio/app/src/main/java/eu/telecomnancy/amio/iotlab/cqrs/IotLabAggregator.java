package eu.telecomnancy.amio.iotlab.cqrs;

import com.google.gson.Gson;

import java.io.IOException;

import eu.telecomnancy.amio.iotlab.Constants;
import eu.telecomnancy.amio.iotlab.cqrs.query.GetMotesBrightnessQuery;
import eu.telecomnancy.amio.iotlab.cqrs.query.GetMotesHumidityQuery;
import eu.telecomnancy.amio.iotlab.cqrs.query.GetMotesTemperatureQuery;
import eu.telecomnancy.amio.iotlab.dto.MoteDtoCollection;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * IotLab CQRS aggregator to handle command and queries
 */
public class IotLabAggregator {

    /**
     * Inner HttpClient used for HTTP requests
     */
    private final OkHttpClient _httpClient = new OkHttpClient();

    /**
     * Raw url prebuilt for fetching brightness
     */
    private final static String BRIGHTNESS_RAW_URL =
            Constants.Urls.Api + "/" + Constants.Labels.BRIGHTNESS + "/last";

    /**
     * Raw url prebuilt for fetching humidity
     */
    private final static String HUMIDITY_RAW_URL =
            Constants.Urls.Api + "/" + Constants.Labels.HUMIDITY + "/last";

    /**
     * Raw url prebuilt for fetching temperature
     */
    private final static String TEMPERATURE_RAW_URL =
            Constants.Urls.Api + "/" + Constants.Labels.TEMPERATURE + "/last";

    /**
     * Retrieve the brightness of all desired motes
     * @param query GetMotesBrightnessQuery query
     * @return The MoteDtoCollection holding all requested data
     * @throws IllegalArgumentException If the number of motes in the query is less or equal than 0
     * @throws IOException If any issue happened with the HTTP call
     */
    public MoteDtoCollection handleGetMotesBrightnessQuery(GetMotesBrightnessQuery query)
            throws IllegalArgumentException, IOException {
        // Build the HTTP Request based on the query
        Request request = new Request.Builder()
                .url(BRIGHTNESS_RAW_URL)
                .build();

        return performAndRetrieveMoteCollectionQueryResult(request);
    }

    /**
     * Retrieve the humidity of all desired motes
     * @param query GetMotesHumidityQuery query
     * @return The MoteDtoCollection holding all requested data
     * @throws IllegalArgumentException If the number of motes in the query is less or equal than 0
     * @throws IOException If any issue happened with the HTTP call
     */
    public MoteDtoCollection handleGetMotesHumidityQuery(GetMotesHumidityQuery query)
            throws IllegalArgumentException, IOException {
        // Build the HTTP Request based on the query
        Request request = new Request.Builder()
                .url(HUMIDITY_RAW_URL)
                .build();

        return performAndRetrieveMoteCollectionQueryResult(request);
    }

    /**
     * Retrieve the temperature of all desired motes
     * @param query GetMotesTemperatureQuery query
     * @return The MoteDtoCollection holding all requested data
     * @throws IllegalArgumentException If the number of motes in the query is less or equal than 0
     * @throws IOException If any issue happened with the HTTP call
     */
    public MoteDtoCollection handleGetMotesTemperatureQuery(GetMotesTemperatureQuery query)
            throws IllegalArgumentException, IOException {
        // Build the HTTP Request based on the query
        Request request = new Request.Builder()
                .url(TEMPERATURE_RAW_URL)
                .build();

        return performAndRetrieveMoteCollectionQueryResult(request);
    }

    /**
     * Performs the HTTP Call to retrieve a mote collection
     * @param request Request to be executed
     * @return A mote collection with its object mapped to the JSON response of the request
     * @throws IOException If any issue happened with the HTTP call
     */
    private MoteDtoCollection performAndRetrieveMoteCollectionQueryResult(Request request)
            throws IOException {
        // Send the HTTP GET request to the API
        Response response = _httpClient
                .newCall(request)
                .execute();

        // Retrieve the payload of the request
        ResponseBody payload = response.body();

        // If any data is fetched, deserialize it into the appropriate object
        return (payload != null)
                ? new Gson().fromJson(payload.string(), MoteDtoCollection.class)
                : new MoteDtoCollection();
    }

}
