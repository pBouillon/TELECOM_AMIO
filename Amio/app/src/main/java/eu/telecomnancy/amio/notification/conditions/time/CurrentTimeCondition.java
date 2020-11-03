package eu.telecomnancy.amio.notification.conditions.time;

import java.util.Calendar;

import eu.telecomnancy.amio.notification.conditions.ICondition;

/**
 * Condition template for all condition that will depend on the current time
 */
public abstract class CurrentTimeCondition implements ICondition {

    /**
     * An instance of a calendar, calibrated to the provided timestamp
     */
    protected final Calendar calendar;

    /**
     * Current time in milliseconds
     */
    protected final long currentTimeInMillis;

    /**
     * Create the condition
     * @param currentTimeInMillis Reference time to be checked
     */
    protected CurrentTimeCondition(long currentTimeInMillis) {
        this.currentTimeInMillis = currentTimeInMillis;

        // This will retrieve an instance of GregorianCalendar
        // see: https://stackoverflow.com/a/6905318/6152689
        calendar = Calendar.getInstance();

        // Calibrate the calendar to the provided timestamp
        calendar.setTimeInMillis(currentTimeInMillis);
    }

}
