package eu.telecomnancy.amio.notification.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

import java.util.stream.Stream;

import eu.telecomnancy.amio.notification.EventContext;
import eu.telecomnancy.amio.notification.EventDispatcher;
import eu.telecomnancy.amio.notification.conditions.ICondition;
import eu.telecomnancy.amio.notification.conditions.sensors.IsNewLightOn;
import eu.telecomnancy.amio.notification.conditions.time.IsEvening;
import eu.telecomnancy.amio.notification.conditions.time.IsWeekEnd;

/**
 * Rule to be activated when a new light is turned on on the week-end's evening
 */
@SuppressWarnings("unused")
@Rule(name = "New light on week-end's evening rule",
        description = "When a new light is detected on the week-end's evening, fire the event")
public class NewLightOnWeekEndEveningRule implements IRule {

    @Condition
    public boolean when(@Fact(EventDispatcher.ContextTag) EventContext context) {
        long currentTime = context.currentTime;

        return Stream.of(new IsNewLightOn(), new IsWeekEnd(currentTime), new IsEvening(currentTime))
                .allMatch(ICondition::evaluate);
    }

    @Action
    public void then(Facts facts) {
        System.out.println("New Light on week end's evening !");
    }

}
