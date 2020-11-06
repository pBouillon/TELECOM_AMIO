package eu.telecomnancy.amio.notification.notifiers;

import eu.telecomnancy.amio.notification.NotificationContext;
import eu.telecomnancy.amio.notification.annotations.EventNotifier;
import eu.telecomnancy.amio.notification.flags.NotificationType;

/**
 * Define a INotifier able to propagate a notification through Android's toasts
 *
 * @see INotifier
 * @see eu.telecomnancy.amio.notification.flags.NotificationType
 */
@EventNotifier(target = NotificationType.ANDROID)
public class ToastNotifier extends AndroidNotifier {

    /**
     * Propagate the context to the super class
     *
     * @param payload The notification payload
     */
    public ToastNotifier(NotificationContext payload) {
        super(payload);
    }

    @Override
    public void sendNotification() {
        System.out.println("Toast from " + VibratorNotifier.class.getSimpleName());
    }

}
