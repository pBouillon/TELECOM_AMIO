package eu.telecomnancy.amio.notification.conditions.time;

import java.util.Calendar;

import eu.telecomnancy.amio.notification.Constants;

/**
 * Condition to check if it is currently the night
 */
public class IsNight extends CurrentTimeCondition {

    /**
     * Create the condition
     * @param currentTimeInMillis Reference time to be checked
     */
    public IsNight(long currentTimeInMillis) {
        super(currentTimeInMillis);
    }

    @Override
    public boolean evaluate() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        return hour >= Constants.TimeSpans.Night.BEGINNING
                || hour < Constants.TimeSpans.Night.END;
    }

}
