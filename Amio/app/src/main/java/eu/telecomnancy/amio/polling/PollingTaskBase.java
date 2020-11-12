package eu.telecomnancy.amio.polling;

import android.util.Log;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

import eu.telecomnancy.amio.iotlab.cqrs.IotLabAggregator;
import eu.telecomnancy.amio.iotlab.cqrs.query.mote.GetMotesBatteryQuery;
import eu.telecomnancy.amio.iotlab.cqrs.query.mote.GetMotesBrightnessQuery;
import eu.telecomnancy.amio.iotlab.cqrs.query.mote.GetMotesDataTypeQuery;
import eu.telecomnancy.amio.iotlab.cqrs.query.mote.GetMotesHumidityQuery;
import eu.telecomnancy.amio.iotlab.cqrs.query.mote.GetMotesTemperatureQuery;
import eu.telecomnancy.amio.iotlab.dto.MoteCollectionDtoAggregator;
import eu.telecomnancy.amio.iotlab.dto.MoteDtoCollection;
import eu.telecomnancy.amio.iotlab.models.Mote;
import eu.telecomnancy.amio.iotlab.models.collections.IMoteCollection;
import eu.telecomnancy.amio.notification.dispatchers.EventDispatcher;
import eu.telecomnancy.amio.polling.contexts.PollingContext;

/**
 * Custom task to be executed to poll the iot lab's server
 */
public abstract class PollingTaskBase extends TimerTask {

    /**
     * Android logging tag for this class
     */
    private static final String TAG = PollingTaskBase.class.getName();

    /**
     * CQRS aggregator to handle command and queries
     */
    private final IotLabAggregator _aggregator;

    /**
     * Rule engine wrapper for event dispatching
     */
    private final EventDispatcher _dispatcher;

    /**
     * Context wrapping the polling data and ecosystem
     */
    private final PollingContext _context;

    /**
     * Create a new polling task
     *
     * @param context Context wrapping the polling data and ecosystem
     */
    public PollingTaskBase(PollingContext context) {
        _context = context;

        _aggregator = new IotLabAggregator(_context);
        _dispatcher = new EventDispatcher(_context);
    }

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
        List<GetMotesDataTypeQuery> motesDataTypeQueries = Arrays.asList(
                new GetMotesBatteryQuery(),
                new GetMotesBrightnessQuery(),
                new GetMotesHumidityQuery(),
                new GetMotesTemperatureQuery()
        );

        // Create the aggregator which will retrieve and merge all DTOs
        MoteCollectionDtoAggregator dtoAggregator = new MoteCollectionDtoAggregator();

        // Perform all queries
        motesDataTypeQueries.forEach(query -> {
            try {
                MoteDtoCollection associatedMoteDtos = _aggregator.handle(query);
                dtoAggregator.aggregateMotesFor(query.label, associatedMoteDtos);
            } catch (IOException e) {
                Log.e(TAG, "Failed to perform the HTTP requests", e);
            }
        });

        // Aggregate all motes and retrieve them
        return dtoAggregator.generateMoteCollectionFromAggregated();
    }

    @Override
    public void run() {
        Log.d(TAG, "Polling task triggered");

        // Retrieve the latest data from the motes
        Log.i(TAG, "Retrieving motes latest data");
        IMoteCollection _motes = getLatestMotes();

        // Call the used-defined callback
        Log.i(TAG, "Calling the callback with the retrieved motes");
        callback(_motes.toList());

        // Fire notifications
        if (!_motes.isEmpty()) {
            Log.i(TAG, "Dispatching notification for the retrieved motes");
            _dispatcher.dispatchNotificationFor(_motes);
        } else {
            Log.w(TAG, "No mote retrieved, skipping the notification dispatch");
        }

        Log.d(TAG, "Polling task executed");
    }

}
