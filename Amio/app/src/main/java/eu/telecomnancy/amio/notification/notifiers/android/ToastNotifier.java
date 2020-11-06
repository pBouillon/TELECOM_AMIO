package eu.telecomnancy.amio.notification.notifiers.android;

import eu.telecomnancy.amio.notification.annotations.EventNotifier;
import eu.telecomnancy.amio.notification.contexts.NotificationContext;
import eu.telecomnancy.amio.notification.flags.NotificationType;
import eu.telecomnancy.amio.notification.notifiers.INotifier;

/**
 * Define a INotifier able to propagate a notification through Android's toasts
 *
 * @see INotifier
 * @see eu.telecomnancy.amio.notification.flags.NotificationType
 */
@EventNotifier(target = NotificationType.ANDROID)
public class ToastNotifier extends AndroidNotifier {

    /**
     * Create a new notifier
     *
     * @param payload Notification payload to be later propagated
     */
    public ToastNotifier(NotificationContext payload) {
        super(payload);
    }

    @Override
    public void sendNotification() {
        System.out.println("Toast from " + VibratorNotifier.class.getSimpleName());
    }

}
