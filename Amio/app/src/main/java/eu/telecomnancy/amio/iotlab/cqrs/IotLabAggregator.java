package eu.telecomnancy.amio.iotlab.cqrs;

import com.google.gson.Gson;

import java.io.IOException;
import java.security.InvalidParameterException;

import eu.telecomnancy.amio.iotlab.Constants;
import eu.telecomnancy.amio.iotlab.cqrs.query.GetMotesBrightnessQuery;
import eu.telecomnancy.amio.iotlab.cqrs.query.GetMotesHumidityQuery;
import eu.telecomnancy.amio.iotlab.cqrs.query.GetMotesTemperatureQuery;
import eu.telecomnancy.amio.iotlab.cqrs.query.IQuery;
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
    private final static String BRIGHTNESS_QUERY_URL =
            Constants.Urls.API + "/" + Constants.Labels.BRIGHTNESS + "/last";

    /**
     * Raw url prebuilt for fetching humidity
     */
    private final static String HUMIDITY_QUERY_URL =
            Constants.Urls.API + "/" + Constants.Labels.HUMIDITY + "/last";

    /**
     * Raw url prebuilt for fetching temperature
     */
    private final static String TEMPERATURE_QUERY_URL =
            Constants.Urls.API + "/" + Constants.Labels.TEMPERATURE + "/last";

    /**
     * Handle the provided IQuery and execute it
     * @param query IQuery to handle
     * @return The MoteDtoCollection holding all requested data according to the provided query
     * @throws IOException If any issue happened with the HTTP call
     * @throws InvalidParameterException If the query can't be handled by the aggregator
     */
    public MoteDtoCollection handle(IQuery query)
            throws IOException, InvalidParameterException {
        // Query the brightness
        if (query instanceof GetMotesBrightnessQuery) {
            return handleGetMotesBrightnessQuery((GetMotesBrightnessQuery) query);
        }

        // Query the humidity
        if (query instanceof GetMotesHumidityQuery) {
            return handleGetMotesHumidityQuery((GetMotesHumidityQuery) query);
        }

        // Query the temperature
        if (query instanceof GetMotesTemperatureQuery) {
            return handleGetMotesTemperatureQuery((GetMotesTemperatureQuery) query);
        }

        // Unknown IQuery or unhandled by the aggregator
        throw new InvalidParameterException(
                "Unhandled query of type " + query.getClass().getTypeName());
    }

    /**
     * Retrieve the brightness of all desired motes
     * @param query GetMotesBrightnessQuery query
     * @return The MoteDtoCollection holding all requested data
     * @throws IOException If any issue happened with the HTTP call
     */
    private MoteDtoCollection handleGetMotesBrightnessQuery(
            @SuppressWarnings("unused") GetMotesBrightnessQuery query)
            throws IOException {
        return fetchAndRetrieveMoteCollectionFromUrl(BRIGHTNESS_QUERY_URL);
    }

    /**
     * Retrieve the humidity of all desired motes
     * @param query GetMotesHumidityQuery query
     * @return The MoteDtoCollection holding all requested data
     * @throws IOException If any issue happened with the HTTP call
     */
    private MoteDtoCollection handleGetMotesHumidityQuery(
            @SuppressWarnings("unused") GetMotesHumidityQuery query)
            throws IOException {
        return fetchAndRetrieveMoteCollectionFromUrl(HUMIDITY_QUERY_URL);
    }

    /**
     * Retrieve the temperature of all desired motes
     * @param query GetMotesTemperatureQuery query
     * @return The MoteDtoCollection holding all requested data
     * @throws IOException If any issue happened with the HTTP call
     */
    private MoteDtoCollection handleGetMotesTemperatureQuery(
            @SuppressWarnings("unused") GetMotesTemperatureQuery query)
            throws IOException {
        return fetchAndRetrieveMoteCollectionFromUrl(TEMPERATURE_QUERY_URL);
    }

    /**
     * Performs the HTTP Call to retrieve a mote collection
     * @param queryUrl Url to be queried
     * @return A mote collection with its object mapped to the JSON response of the request
     * @throws IOException If any issue happened with the HTTP call
     */
    private MoteDtoCollection fetchAndRetrieveMoteCollectionFromUrl(String queryUrl)
            throws IOException {
        // Build the HTTP Request based on the query
        Request request = new Request.Builder()
                .url(queryUrl)
                .build();

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
