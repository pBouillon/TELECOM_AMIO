package eu.telecomnancy.amio.notification.notifiers.android;

import android.util.Log;

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
     * Android logging tag for this class
     */
    private static final String TAG = VibratorNotifier.class.getName();

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
        Log.d(TAG, "Sending notification");
    }

}
