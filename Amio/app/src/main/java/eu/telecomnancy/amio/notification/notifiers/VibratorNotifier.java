package eu.telecomnancy.amio.notification.notifiers;

import eu.telecomnancy.amio.notification.NotificationContext;
import eu.telecomnancy.amio.notification.flags.NotificationType;
import eu.telecomnancy.amio.notification.annotations.EventNotifier;

@EventNotifier(target = NotificationType.ANDROID)
public class VibratorNotifier extends AndroidNotifier {

    public VibratorNotifier(NotificationContext payload) {
        super(payload);
    }

    @Override
    public void sendNotification() {
        System.out.println("Bzzzzzzzzz from " + VibratorNotifier.class.getSimpleName());
    }

}
