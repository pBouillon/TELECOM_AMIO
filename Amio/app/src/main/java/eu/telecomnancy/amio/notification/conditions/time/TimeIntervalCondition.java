package eu.telecomnancy.amio.notification.conditions.time;

import java.time.LocalTime;
import java.util.Calendar;

/**
 * TODO: doc
 */
public abstract class TimeIntervalCondition extends CurrentTimeCondition {

    /**
     * TODO: doc
     */
    // FIXME: this can be private
    protected final long endTimeInMillis;

    /**
     * TODO: doc
     */
    // FIXME: this can be private
    protected final long startTimeInMillis;

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

    /**
     * TODO: doc
     *
     * @return
     */
    public boolean isCurrentTimeBetweenBounds() {
        // A static method may be extracted here, e.g: private static LocalTime getLocalTimeForMillis(int millis)
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTimeInMillis);
        LocalTime start = LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        calendar.setTimeInMillis(endTimeInMillis);
        LocalTime end = LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        calendar.setTimeInMillis(currentTimeInMillis);
        LocalTime current = LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

        // FIXME: this may be split in several bool & methods; e.g:
        //          - private boolean isCurrentTimeWithinRange()
        //          - etc.
        //        you can then merge this to make it more explanatory:
        //        return isCurrentTimeWithinRange()
        //              && isYourOtherTimeSpanCheckMethod();
        return (start.isBefore(current) && current.isBefore(end)) ||
                (end.isBefore(start) && (start.isBefore(current) || current.isBefore(end)));
    }

}
