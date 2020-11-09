package eu.telecomnancy.amio.notification.notifiers.android;

import eu.telecomnancy.amio.notification.contexts.NotificationContext;
import eu.telecomnancy.amio.notification.notifiers.INotifier;
import eu.telecomnancy.amio.notification.notifiers.NotifierBase;

/**
 * Define a INotifier able to propagate a notification on the current Android device
 *
 * @see INotifier
 * @see eu.telecomnancy.amio.notification.flags.NotificationType
 */
public abstract class AndroidNotifier extends NotifierBase {

    /**
     * Create a new notifier
     *
     * @param payload Notification payload to be later propagated
     */
    protected AndroidNotifier(NotificationContext payload) {
        super(payload);
    }

}
