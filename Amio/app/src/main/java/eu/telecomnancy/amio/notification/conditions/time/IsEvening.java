package eu.telecomnancy.amio.notification.conditions.time;

/**
 * Condition to check if it is currently the evening
 */
public class IsEvening extends TimeIntervalCondition {

    /**
     * Create the condition
     *
     * @param currentTimeInMillis Reference time to be checked
     * @param startTimeInMillis   Start time to be checked
     * @param endTimeInMillis     End time to be checked
     */
    public IsEvening(long currentTimeInMillis, long startTimeInMillis, long endTimeInMillis) {
        super(currentTimeInMillis, startTimeInMillis, endTimeInMillis);
    }

    @Override
    public boolean evaluate() {
        return isCurrentTimeBetweenBounds();
    }

}
