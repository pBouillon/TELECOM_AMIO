package eu.telecomnancy.amio.notification.contexts;

import java.util.Date;

import eu.telecomnancy.amio.iotlab.models.ConsecutiveMoteMeasuresPair;
import eu.telecomnancy.amio.polling.contexts.PollingContext;

/**
 * Define a POJO that will wrap all data relative to an event
 */
public class EventContext {

    /**
     * Time at which the context was created in milliseconds
     */
    public final long currentTime = new Date().getTime();

    /**
     * Pair of the two latest values retrieved by a mote
     */
    public final ConsecutiveMoteMeasuresPair consecutiveMoteMeasuresPair;

    /**
     * Context wrapping the polling data and ecosystem
     */
    public final PollingContext pollingContext;

    /**
     * Create an event context from no content, used to dispatch notification from no data
     *
     * @param context Context wrapping the polling data and ecosystem
     */
    public EventContext(PollingContext context) {
        pollingContext = context;
        consecutiveMoteMeasuresPair = null;
    }

    /**
     * Create the event context
     *
     * @param context Context wrapping the polling data and ecosystem
     * @param consecutiveMoteMeasuresPair Collection of the new motes and their previous
     *                                     retrieved values
     */
    public EventContext(PollingContext context,
                        ConsecutiveMoteMeasuresPair consecutiveMoteMeasuresPair) {
        this.consecutiveMoteMeasuresPair = consecutiveMoteMeasuresPair;
        pollingContext = context;
    }



}
