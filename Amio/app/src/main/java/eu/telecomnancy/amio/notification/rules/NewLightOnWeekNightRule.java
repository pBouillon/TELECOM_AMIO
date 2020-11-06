package eu.telecomnancy.amio.notification.rules;

import org.jeasy.rules.annotation.Rule;

import java.util.stream.Stream;

import eu.telecomnancy.amio.notification.contexts.EventContext;
import eu.telecomnancy.amio.notification.conditions.ICondition;
import eu.telecomnancy.amio.notification.conditions.motes.IsAnyNewLightOn;
import eu.telecomnancy.amio.notification.conditions.time.IsNight;
import eu.telecomnancy.amio.notification.conditions.time.IsNotWeekEnd;
import eu.telecomnancy.amio.notification.flags.NotificationType;

/**
 * Rule to be activated when a new light is turned on on the week's night
 */
@Rule(name = "New light on week's night rule",
        description = "When a new light is detected on the week's night, fire the event")
public class NewLightOnWeekNightRule extends RuleBase {

    @Override
    public NotificationType getNotificationTargets() {
        return NotificationType.EMAIL;
    }

    @Override
    public boolean isActiveWhen(EventContext context) {
        long currentTime = context.currentTime;

        return Stream.of(
                new IsAnyNewLightOn(context.motes),
                new IsNotWeekEnd(currentTime),
                new IsNight(currentTime))
                .allMatch(ICondition::evaluate);
    }

}
