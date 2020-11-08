package eu.telecomnancy.amio.notification.conditions.time;

import java.util.Calendar;

/**
 * Condition to check if it is currently the week-end
 */
public class IsWeekEnd extends CurrentTimeCondition {

    /**
     * Create the condition
     *
     * @param currentTimeInMillis Reference time to be checked
     */
    public IsWeekEnd(long currentTimeInMillis) {
        super(currentTimeInMillis);
    }

    @Override
    public boolean evaluate() {
        int currentDayIndex = calendar.get(Calendar.DAY_OF_WEEK);

        return currentDayIndex == Calendar.SATURDAY
                || currentDayIndex == Calendar.SUNDAY;
    }

}
