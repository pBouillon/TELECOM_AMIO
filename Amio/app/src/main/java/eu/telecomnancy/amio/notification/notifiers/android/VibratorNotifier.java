package eu.telecomnancy.amio.notification.notifiers.android;

import eu.telecomnancy.amio.notification.annotations.EventNotifier;
import eu.telecomnancy.amio.notification.contexts.NotificationContext;
import eu.telecomnancy.amio.notification.flags.NotificationType;
import eu.telecomnancy.amio.notification.notifiers.INotifier;

/**
 * Define a INotifier able to propagate a notification on the current Android device's vibrator
 *
 * @see INotifier
 * @see eu.telecomnancy.amio.notification.flags.NotificationType
 */
@EventNotifier(target = NotificationType.ANDROID)
public class VibratorNotifier extends AndroidNotifier {

    /**
     * Create a new notifier
     *
     * @param payload Notification payload to be later propagated
     */
    public VibratorNotifier(NotificationContext payload) {
        super(payload);
    }

    @Override
    public void sendNotification() {
        System.out.println("Bzzzzzzzzz from " + VibratorNotifier.class.getSimpleName());
    }

}
