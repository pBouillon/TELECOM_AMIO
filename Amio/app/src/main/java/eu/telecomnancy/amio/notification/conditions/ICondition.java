package eu.telecomnancy.amio.notification.conditions;

/**
 * Define an atomic condition to be verified by the rules
 *
 * @see eu.telecomnancy.amio.notification.rules.IRule
 */
public interface ICondition {

    /**
     * Evaluate the condition
     *
     * @return true if the condition is validated; false otherwise
     */
    boolean evaluate();

}
