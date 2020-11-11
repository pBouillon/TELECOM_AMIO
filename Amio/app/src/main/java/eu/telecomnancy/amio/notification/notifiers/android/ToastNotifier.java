package eu.telecomnancy.amio.notification.notifiers.android;

import android.util.Log;

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
     * Android logging tag for this class
     */
    private static final String TAG = ToastNotifier.class.getName();

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
        Log.d(TAG, "Sending notification");
    }

}
