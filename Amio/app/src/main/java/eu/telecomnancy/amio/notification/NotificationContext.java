package eu.telecomnancy.amio.notification;

import eu.telecomnancy.amio.notification.flags.NotificationType;

public class NotificationContext {

    public final EventContext context;

    public final NotificationType type;

    public NotificationContext(NotificationType notificationType, EventContext notificationContext) {
        context = notificationContext;
        type = notificationType;
    }

}
