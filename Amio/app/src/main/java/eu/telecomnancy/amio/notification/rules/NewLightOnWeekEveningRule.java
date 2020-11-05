package eu.telecomnancy.amio.notification.rules;

import org.jeasy.rules.annotation.Rule;

import java.util.stream.Stream;

import eu.telecomnancy.amio.notification.EventContext;
import eu.telecomnancy.amio.notification.conditions.ICondition;
import eu.telecomnancy.amio.notification.conditions.motes.IsAnyNewLightOn;
import eu.telecomnancy.amio.notification.conditions.time.IsEvening;
import eu.telecomnancy.amio.notification.conditions.time.IsNotWeekEnd;
import eu.telecomnancy.amio.notification.flags.NotificationType;

/**
 * Rule to be activated when a new light is turned on on the week's evening
 */
@Rule(name = "New light on week's evening rule",
        description = "When a new light is detected on the week's evening, fire the event")
public class NewLightOnWeekEveningRule extends RuleBase {

    @Override
    public NotificationType getNotificationTargets() {
        return NotificationType.ANDROID;
    }

    @Override
    public boolean isActiveWhen(EventContext context) {
        long currentTime = context.currentTime;

        return Stream.of(
                new IsAnyNewLightOn(context.motes),
                new IsNotWeekEnd(currentTime),
                new IsEvening(currentTime))
                .allMatch(ICondition::evaluate);
    }

}
