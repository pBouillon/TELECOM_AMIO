package eu.telecomnancy.amio.notification.notifiers.email;

import eu.telecomnancy.amio.notification.annotations.EventNotifier;
import eu.telecomnancy.amio.notification.contexts.NotificationContext;
import eu.telecomnancy.amio.notification.flags.NotificationType;
import eu.telecomnancy.amio.notification.notifiers.INotifier;
import eu.telecomnancy.amio.notification.notifiers.NotifierBase;

/**
 * Define a INotifier able to propagate a notification by email
 *
 * @see INotifier
 * @see eu.telecomnancy.amio.notification.flags.NotificationType
 */
@EventNotifier(target = NotificationType.EMAIL)
public abstract class EmailNotifier extends NotifierBase {

    /**
     * Create a new notifier
     *
     * @param payload Notification payload to be later propagated
     */
    public EmailNotifier(NotificationContext payload) {
        super(payload);
    }

}
