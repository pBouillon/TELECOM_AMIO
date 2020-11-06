package eu.telecomnancy.amio.notification.rules;

import org.jeasy.rules.api.Facts;

import eu.telecomnancy.amio.notification.EventContext;
import eu.telecomnancy.amio.notification.dispatchers.EventDispatcher;

/**
 * Define a rule to be checked by the rule engine when a new event is fired
 *
 * @see EventDispatcher
 */
public interface IRule {

    /**
     * Test a condition against the provided context
     * This rule is considered as active when the condition is met
     *
     * @param context Context against which performing the checks
     * @return true whenever the condition is met; false otherwise
     */
    boolean isActiveWhen(EventContext context);

    /**
     * Action to be performed when `isActiveWhen` is true
     *
     * @param facts Context propagated by the rule engine
     */
    void then(Facts facts);

}
