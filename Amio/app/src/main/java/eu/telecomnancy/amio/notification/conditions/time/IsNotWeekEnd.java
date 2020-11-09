package eu.telecomnancy.amio.notification.conditions.time;

/**
 * Condition to check if it is currently not the week-end
 */
public class IsNotWeekEnd extends CurrentTimeCondition {

    /**
     * Create the condition
     *
     * @param currentTimeInMillis Reference time to be checked
     */
    public IsNotWeekEnd(long currentTimeInMillis) {
        super(currentTimeInMillis);
    }

    @Override
    public boolean evaluate() {
        return !new IsWeekEnd(currentTimeInMillis)
                .evaluate();
    }

}
