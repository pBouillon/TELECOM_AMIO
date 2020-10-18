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
     * Retrieve the brightness of all desired motes
     * @param query GetMotesBrightnessQuery query
     * @return The MoteDtoCollection holding all requested data
     * @throws IllegalArgumentException If the number of motes in the query is less or equal than 0
     * @throws IOException If any issue happened with the HTTP call
     */
    public MoteDtoCollection handleGetMotesBrightnessQuery(GetMotesBrightnessQuery query)
            throws IllegalArgumentException, IOException {
        // TODO: keep it DRY
        // Ensure that a valid number of motes to be queried is provided
        if (query.getNumberOfQueriedMotes() <= 0) {
            throw new IllegalArgumentException(
                    "The number of queried mote should be more than 0 " +
                            "(actual: " + query.getNumberOfQueriedMotes() + ")");
        }

        // Build the HTTP Request
        Request request = new Request.Builder()
                // TODO: create constant
                .url(Constants.Urls.LOCAL + "/light1/" + query.getNumberOfQueriedMotes())
                .build();

        return performAndRetrieveQueryResult(request);
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
        // TODO: keep it DRY
        // Ensure that a valid number of motes to be queried is provided
        if (query.getNumberOfQueriedMotes() <= 0) {
            throw new IllegalArgumentException(
                    "The number of queried mote should be more than 0 " +
                            "(actual: " + query.getNumberOfQueriedMotes() + ")");
        }

        // Build the HTTP Request
        Request request = new Request.Builder()
                // TODO: create constant
                .url(Constants.Urls.LOCAL + "/humidity/" + query.getNumberOfQueriedMotes())
                .build();

        return performAndRetrieveQueryResult(request);
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
        // TODO: keep it DRY
        // Ensure that a valid number of motes to be queried is provided
        if (query.getNumberOfQueriedMotes() <= 0) {
            throw new IllegalArgumentException(
                    "The number of queried mote should be more than 0 " +
                            "(actual: " + query.getNumberOfQueriedMotes() + ")");
        }

        // Build the HTTP Request
        Request request = new Request.Builder()
                // TODO: create constant
                .url(Constants.Urls.LOCAL + "/temperature/" + query.getNumberOfQueriedMotes())
                .build();

        return performAndRetrieveQueryResult(request);
    }

    private MoteDtoCollection performAndRetrieveQueryResult(Request request) throws IOException {
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
