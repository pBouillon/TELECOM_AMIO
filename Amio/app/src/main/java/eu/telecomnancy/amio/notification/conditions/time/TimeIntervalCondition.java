package eu.telecomnancy.amio.notification.conditions.time;

import java.time.LocalTime;
import java.util.Calendar;

public abstract class TimeIntervalCondition extends CurrentTimeCondition {

    protected final long startTimeInMillis;

    protected final long endTimeInMillis;

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

    public boolean isCurrentTimeBetweenBounds() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTimeInMillis);
        LocalTime start = LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        calendar.setTimeInMillis(endTimeInMillis);
        LocalTime end = LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        calendar.setTimeInMillis(currentTimeInMillis);
        LocalTime current = LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

        return (start.isBefore(current) && current.isBefore(end)) ||
                (end.isBefore(start) && (start.isBefore(current) || current.isBefore(end)));
    }
}
