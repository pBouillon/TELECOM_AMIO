package eu.telecomnancy.amio.notification.notifiers;

import eu.telecomnancy.amio.notification.NotificationContext;

/**
 * Define a INotifier able to propagate a notification on the current Android device
 *
 * @see INotifier
 * @see eu.telecomnancy.amio.notification.flags.NotificationType
 */
public abstract class AndroidNotifier extends NotifierBase {

    /**
     * Propagate the context to the super class
     *
     * @param payload The notification payload
     */
    protected AndroidNotifier(NotificationContext payload) {
        super(payload);
    }

}
