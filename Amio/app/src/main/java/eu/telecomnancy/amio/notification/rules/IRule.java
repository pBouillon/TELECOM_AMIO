package eu.telecomnancy.amio.notification.rules;

import org.jeasy.rules.api.Facts;

import eu.telecomnancy.amio.notification.EventContext;

/**
 * Define a rule to be checked by the rule engine when a new event is fired
 *
 * @see eu.telecomnancy.amio.notification.EventDispatcher
 */
public interface IRule {

    boolean isActiveWhen(EventContext context);

    void then(Facts facts);

}
