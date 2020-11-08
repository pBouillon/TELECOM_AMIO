package eu.telecomnancy.amio.notification;

import java.util.Date;

import eu.telecomnancy.amio.iotlab.entities.collections.IMoteCollection;

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
     * Create the event context
     *
     * @param fetchedMotes Motes retrieved to be passed to the rule engine
     */
    public EventContext(IMoteCollection fetchedMotes) {
        motes = fetchedMotes;
    }

}
