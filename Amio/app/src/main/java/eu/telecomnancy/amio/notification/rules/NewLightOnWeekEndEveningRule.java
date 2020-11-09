package eu.telecomnancy.amio.notification.rules;

import org.jeasy.rules.annotation.Rule;

import java.util.stream.Stream;

import eu.telecomnancy.amio.notification.contexts.EventContext;
import eu.telecomnancy.amio.notification.conditions.ICondition;
import eu.telecomnancy.amio.notification.conditions.motes.IsAnyNewLightOn;
import eu.telecomnancy.amio.notification.conditions.time.IsEvening;
import eu.telecomnancy.amio.notification.conditions.time.IsWeekEnd;
import eu.telecomnancy.amio.notification.flags.NotificationType;

/**
 * Rule to be activated when a new light is turned on on the week-end's evening
 */
@Rule(name = "New light on week-end's evening rule",
        description = "When a new light is detected on the week-end's evening, fire the event")
public class NewLightOnWeekEndEveningRule extends RuleBase {

    @Override
    public NotificationType getNotificationTargets() {
        return NotificationType.EMAIL;
    }

    @Override
    public boolean isActiveWhen(EventContext context) {
        long currentTime = context.currentTime;

        return Stream.of(
                new IsAnyNewLightOn(context.motes),
                new IsWeekEnd(currentTime),
                new IsEvening(currentTime))
                .allMatch(ICondition::evaluate);
    }

}
