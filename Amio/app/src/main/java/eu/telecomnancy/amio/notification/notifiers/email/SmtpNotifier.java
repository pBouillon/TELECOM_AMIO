package eu.telecomnancy.amio.notification.notifiers.email;

import android.util.Log;

import eu.telecomnancy.amio.notification.annotations.EventNotifier;
import eu.telecomnancy.amio.notification.contexts.NotificationContext;
import eu.telecomnancy.amio.notification.flags.NotificationType;
import eu.telecomnancy.amio.notification.notifiers.INotifier;

/**
 * Define a INotifier able to propagate a notification by mail using an SMTP server
 *
 * @see INotifier
 * @see eu.telecomnancy.amio.notification.flags.NotificationType
 */
@EventNotifier(target = NotificationType.EMAIL)
public class SmtpNotifier extends EmailNotifier {

    /**
     * Android logging tag for this class
     */
    private static final String TAG = SmtpNotifier.class.getName();

    /**
     * Create a new notifier
     *
     * @param payload Notification payload to be later propagated
     */
    public SmtpNotifier(NotificationContext payload) {
        super(payload);
    }

    @Override
    public void sendNotification() {
        Log.d(TAG, "Sending notification");
    }

}
