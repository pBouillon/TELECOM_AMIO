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

    boolean isActiveWhen(EventContext context);

    void then(Facts facts);

}
