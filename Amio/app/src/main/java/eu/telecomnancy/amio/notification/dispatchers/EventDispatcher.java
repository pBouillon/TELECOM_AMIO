package eu.telecomnancy.amio.notification.dispatchers;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.util.Arrays;
import java.util.List;

import eu.telecomnancy.amio.iotlab.entities.collections.IMoteCollection;
import eu.telecomnancy.amio.notification.EventContext;
import eu.telecomnancy.amio.notification.rules.IRule;
import eu.telecomnancy.amio.notification.rules.NewLightOnWeekEndEveningRule;
import eu.telecomnancy.amio.notification.rules.NewLightOnWeekEveningRule;
import eu.telecomnancy.amio.notification.rules.NewLightOnWeekNightRule;

/**
 * Rule engine wrapper handling the rules registration and the firing of all notification events
 */
public class EventDispatcher {

    /**
     * Tag under which the context passed to the Rules' Facts will be registered
     *
     * @see Facts
     * @see org.jeasy.rules.annotation.Rule
     */
    public static final String ContextTag = "Context";

    /**
     * Registered rules handler
     */
    private final Rules _rules = new Rules();

    /**
     * Easy-Rules rule engine
     */
    private final RulesEngine _ruleEngine = new DefaultRulesEngine();

    /**
     * Inner list of all rules evaluated by the engine
     */
    private static final List<IRule> handledRules = Arrays.asList(
            new NewLightOnWeekEndEveningRule(),
            new NewLightOnWeekEveningRule(),
            new NewLightOnWeekNightRule()
    );

    /**
     * Default constructor
     * This will register all rules to be later evaluated on event firing
     */
    public EventDispatcher() {
        handledRules.forEach(_rules::register);
    }

    /**
     * Evaluate the provided data against all registered rules for notification
     *
     * @param fetchedMotes A collection of all fetched motes to be passed to all rules
     * @see EventContext
     */
    public void dispatchNotificationFor(IMoteCollection fetchedMotes) {
        // Create the event context from the provided motes
        EventContext context = new EventContext(fetchedMotes);

        // Create the rules payload from the context
        Facts facts = new Facts();
        facts.put(ContextTag, context);

        // Fire the event context against all the registered rules
        _ruleEngine.fire(_rules, facts);
    }

}
