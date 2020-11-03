package eu.telecomnancy.amio.polling;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

import eu.telecomnancy.amio.iotlab.cqrs.IotLabAggregator;
import eu.telecomnancy.amio.iotlab.cqrs.query.GetMotesBrightnessQuery;
import eu.telecomnancy.amio.iotlab.cqrs.query.GetMotesHumidityQuery;
import eu.telecomnancy.amio.iotlab.cqrs.query.GetMotesTemperatureQuery;
import eu.telecomnancy.amio.iotlab.dto.MoteCollectionDtoAggregator;
import eu.telecomnancy.amio.iotlab.entities.Mote;
import eu.telecomnancy.amio.iotlab.entities.collections.IMoteCollection;
import eu.telecomnancy.amio.iotlab.entities.collections.MoteCollection;

/**
 * Custom task to be executed to poll the iot lab's server
 */
public abstract class PollingTaskBase extends TimerTask {

    /**
     * Android logging tag for this class
     */
    private static final String TAG = PollingTaskBase.class.getSimpleName();

    /**
     * CQRS aggregator to handle command and queries
     */
    private final IotLabAggregator _aggregator = new IotLabAggregator();

    /**
     * Motes retrieved and handled by the polling task
     */
    private IMoteCollection _motes = new MoteCollection();

    /**
     * Define a custom callback method to be executed when the task has run its job
     *
     * This custom callback methods allows the caller to use all data provided as parameter in its
     * scope without exposing any property
     */
    public abstract void callback(List<Mote> motes);

    /**
     * Retrieve the latest motes data from the remote API and returns a structured IMoteCollection
     *
     * This will query the temperature, the humidity and the brightness of all motes and aggregate
     * them in `_motes`
     *
     * @see IMoteCollection
     */
    private IMoteCollection getLatestMotes() {
        // Prepare all queries
        GetMotesBrightnessQuery getBrightnessQuery = new GetMotesBrightnessQuery();

        GetMotesHumidityQuery getHumidityQuery = new GetMotesHumidityQuery();

        GetMotesTemperatureQuery getTemperaturesQuery = new GetMotesTemperatureQuery();

        // Create the aggregator which will retrieve and merge all DTOs
        MoteCollectionDtoAggregator dtoAggregator = new MoteCollectionDtoAggregator();

        // Perform all queries
        try {
            dtoAggregator.setBrightnessCollectionDto(
                    _aggregator.handle(getBrightnessQuery));

            dtoAggregator.setHumidityCollectionDto(
                    _aggregator.handle(getHumidityQuery));

            dtoAggregator.setTemperatureCollectionDto(
                    _aggregator.handle(getTemperaturesQuery));
        } catch (IOException e) {
            Log.e(TAG, "Failed to perform the HTTP requests", e);
        }

        // Aggregate all motes and retrieve them
        return dtoAggregator.generateMoteCollectionFromAggregated();
    }

    @Override
    public void run() {
        Log.i(TAG, "Polling task triggered");

        // Retrieve the latest data from the motes
        _motes = getLatestMotes();

        // Call the used-defined callback
        callback(_motes.toList());

        Log.i(TAG, "Polling task successfully executed");
    }

}
