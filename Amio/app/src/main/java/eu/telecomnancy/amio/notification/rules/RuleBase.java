package eu.telecomnancy.amio.notification.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.api.Facts;

import eu.telecomnancy.amio.notification.EventContext;
import eu.telecomnancy.amio.notification.dispatchers.EventDispatcher;
import eu.telecomnancy.amio.notification.NotificationContext;
import eu.telecomnancy.amio.notification.flags.NotificationType;
import eu.telecomnancy.amio.notification.dispatchers.NotificationDispatcher;

public abstract class RuleBase implements IRule {

    @Condition
    public final boolean when(@Fact(EventDispatcher.ContextTag) EventContext context) {
        return isActiveWhen(context);
    }

    @Action
    @Override
    public void then(Facts facts) {
        EventContext context = (EventContext) facts
                .getFact(EventDispatcher.ContextTag)
                .getValue();

        NotificationContext notificationContext =
                new NotificationContext(getNotificationTargets(), context);

        NotificationDispatcher.sendNotificationsFrom(notificationContext);
    }

    protected abstract NotificationType getNotificationTargets();

}
