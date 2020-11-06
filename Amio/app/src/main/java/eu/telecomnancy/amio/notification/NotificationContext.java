package eu.telecomnancy.amio.notification;

import eu.telecomnancy.amio.notification.flags.NotificationType;

/**
 * Define a POJO that will wrap all data relative to a notification
 */
public class NotificationContext {

    /**
     * Context of the event to be propagated
     */
    public final EventContext context;

    /**
     * Type of the notification to be triggered
     */
    public final NotificationType type;

    /**
     * Create the notification context
     *
     * @param notificationType Type of the notification to be triggered
     * @param notificationContext Context of the event to be propagated
     */
    public NotificationContext(NotificationType notificationType, EventContext notificationContext) {
        context = notificationContext;
        type = notificationType;
    }

}
