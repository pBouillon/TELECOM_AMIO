package eu.telecomnancy.amio.polling.contexts;

import android.content.Context;

/**
 * Define a POJO that will wrap all data relative to the polling routines
 */
public class PollingContext {

    /**
     * Android context in which the polling is made
     */
    public final Context androidContext;

    /**
     * Create the polling context
     *
     * @param androidContext Android context in which the polling is made
     */
    public PollingContext(Context androidContext) {
        this.androidContext = androidContext;
    }

}
