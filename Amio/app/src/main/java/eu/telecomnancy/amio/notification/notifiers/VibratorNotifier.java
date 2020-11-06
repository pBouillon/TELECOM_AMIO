package eu.telecomnancy.amio.notification.notifiers;

import eu.telecomnancy.amio.notification.NotificationContext;
import eu.telecomnancy.amio.notification.flags.NotificationType;
import eu.telecomnancy.amio.notification.annotations.EventNotifier;

/**
 * Define a INotifier able to propagate a notification on the current Android device's vibrator
 *
 * @see INotifier
 * @see eu.telecomnancy.amio.notification.flags.NotificationType
 */
@EventNotifier(target = NotificationType.ANDROID)
public class VibratorNotifier extends AndroidNotifier {

    /**
     * Propagate the context to the super class
     *
     * @param payload The notification payload
     */
    public VibratorNotifier(NotificationContext payload) {
        super(payload);
    }

    @Override
    public void sendNotification() {
        System.out.println("Bzzzzzzzzz from " + VibratorNotifier.class.getSimpleName());
    }

}
