package eu.telecomnancy.amio.notification.contexts;

import java.util.Date;

import eu.telecomnancy.amio.iotlab.models.collections.IMoteCollection;
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
     * Collection of retrieved motes
     */
    public final IMoteCollection motes;

    /**
     * Context wrapping the polling data and ecosystem
     */
    public final PollingContext pollingContext;

    /**
     * Create the event context
     *
     * @param context Context wrapping the polling data and ecosystem
     * @param fetchedMotes Motes retrieved to be passed to the rule engine
     */
    public EventContext(PollingContext context, IMoteCollection fetchedMotes) {
        pollingContext = context;
        motes = fetchedMotes;
    }



}
