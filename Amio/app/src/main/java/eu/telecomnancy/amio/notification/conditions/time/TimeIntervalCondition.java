package eu.telecomnancy.amio.notification.conditions.time;

import java.time.LocalTime;
import java.util.Calendar;

/**
 * Condition template for all condition that will depend on the current time and a time interval
 */
public abstract class TimeIntervalCondition extends CurrentTimeCondition {

    /**
     * Timestamp representing the end of the interval
     * Use only Hour and Minutes from this timestamp
     */
    private final long endTimeInMillis;

    /**
     * Timestamp representing the start of the interval
     * Use only Hour and Minutes from this timestamp
     */
    private final long startTimeInMillis;

    /**
     * Create the condition
     *
     * @param currentTimeInMillis Reference time to be checked
     * @param startTimeInMillis   Start time to be checked
     * @param endTimeInMillis     End time to be checked
     */
    protected TimeIntervalCondition(long currentTimeInMillis, long startTimeInMillis, long endTimeInMillis) {
        super(currentTimeInMillis);

        this.startTimeInMillis = startTimeInMillis;
        this.endTimeInMillis = endTimeInMillis;
    }

    private static LocalTime getLocalTimeForMillis(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
    }

    /**
     * @return true if the current time is in the time range. It takes care of time span that start on evening and end the next day morning
     */
    public boolean isCurrentTimeBetweenBounds() {
        LocalTime start = getLocalTimeForMillis(startTimeInMillis);
        LocalTime end = getLocalTimeForMillis(endTimeInMillis);
        LocalTime current = getLocalTimeForMillis(currentTimeInMillis);

        // First part handle the case when start < end (both time on the same day)
        // Second part handle the case when start > end (ending time set to the next day morning)
        return (start.isBefore(current) && current.isBefore(end)) ||
                (end.isBefore(start) && (start.isBefore(current) || current.isBefore(end)));
    }

}
