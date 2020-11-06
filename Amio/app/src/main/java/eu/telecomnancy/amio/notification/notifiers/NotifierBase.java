package eu.telecomnancy.amio.notification.notifiers;

import eu.telecomnancy.amio.notification.NotificationContext;

/**
 * Define a generic INotifier able to propagate a notification
 *
 * @see INotifier
 * @see eu.telecomnancy.amio.notification.flags.NotificationType
 */
public abstract class NotifierBase implements INotifier {

    /**
     * Notification payload to be later propagated
     */
    protected final NotificationContext payload;

    /**
     * Create a new notifier
     *
     * @param payload Notification payload to be later propagated
     */
    public NotifierBase(NotificationContext payload) {
        this.payload = payload;
    }

}
