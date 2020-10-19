package eu.telecomnancy.amio.iotlab;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
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
     * Default number of motes queried when querying several entities on the IoTLab API
     */
    private static final int DEFAULT_MOTES_QUERIED_AMOUNT = 50;

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
     * Populate the inner-collection of the motes handled by the task
     *
     * This will query the temperature, the humidity and the brightness of all motes and aggregate
     * them in `_motes`
     */
    private void populateMotes() {
        // Prepare all queries
        GetMotesBrightnessQuery getBrightnessQuery =
                new GetMotesBrightnessQuery(DEFAULT_MOTES_QUERIED_AMOUNT);

        GetMotesHumidityQuery getHumidityQuery =
                new GetMotesHumidityQuery(DEFAULT_MOTES_QUERIED_AMOUNT);

        GetMotesTemperatureQuery getTemperaturesQuery =
                new GetMotesTemperatureQuery(DEFAULT_MOTES_QUERIED_AMOUNT);

        // Create the aggregator which will retrieve and merge all DTOs
        MoteCollectionDtoAggregator dtoAggregator = new MoteCollectionDtoAggregator();

        // Perform all queries
        try {
            dtoAggregator.setBrightnessCollectionDto(
                    _aggregator.handleGetMotesBrightnessQuery(getBrightnessQuery));

            dtoAggregator.setHumidityCollectionDto(
                    _aggregator.handleGetMotesHumidityQuery(getHumidityQuery));

            dtoAggregator.setTemperatureCollectionDto(
                    _aggregator.handleGetMotesTemperatureQuery(getTemperaturesQuery));
        } catch (IOException e) {
            Log.e(TAG, "Failed to perform the HTTP requests", e);
        }

        // Aggregate all motes and retrieve them
        _motes = dtoAggregator.generateMoteCollectionFromAggregated();
    }

    @Override
    public void run() {
        Log.i(TAG, "Polling task triggered");

        if (_motes.isEmpty()) {
            // If no motes are registered, populate the collection
            populateMotes();
        } else {
            // Otherwise, update its content
            updateLastMotes();
        }

        // Call the used-defined callback
        callback(_motes.toList());

        Log.i(TAG, "Polling task successfully executed");
    }

    /**
     * Update the lastly updates motes
     *
     * For all motes of the collection this will update the ones with the most recent data for the
     * temperature, the humidity and the brightness
     */
    private void updateLastMotes() {
        // TODO
    }

}
