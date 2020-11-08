package eu.telecomnancy.amio.notification.conditions.time;

import java.util.Calendar;

import eu.telecomnancy.amio.notification.Constants;

/**
 * Condition to check if it is currently the evening
 */
public class IsEvening extends CurrentTimeCondition {

    /**
     * Create the condition
     *
     * @param currentTimeInMillis Reference time to be checked
     */
    public IsEvening(long currentTimeInMillis) {
        super(currentTimeInMillis);
    }

    @Override
    public boolean evaluate() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        return hour >= Constants.TimeSpans.Evening.BEGINNING
                && hour < Constants.TimeSpans.Evening.END;
    }

}
