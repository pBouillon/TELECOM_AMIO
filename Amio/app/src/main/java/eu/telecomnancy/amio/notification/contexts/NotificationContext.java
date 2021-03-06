package eu.telecomnancy.amio.notification.contexts;

import eu.telecomnancy.amio.notification.flags.NotificationType;

/**
 * Define a POJO that will wrap all data relative to a notification
 */
public class NotificationContext {

    /**
     * Context of the event to be propagated
     */
    public final EventContext eventContext;

    /**
     * Type of the notification to be triggered
     */
    public final NotificationType type;

    /**
     * Create the notification context
     *
     * @param notificationType Type of the notification to be triggered
     * @param eventContext Context of the event to be propagated
     */
    public NotificationContext(NotificationType notificationType, EventContext eventContext) {
        this.eventContext = eventContext;
        type = notificationType;
    }

}
