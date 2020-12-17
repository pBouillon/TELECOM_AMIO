package eu.telecomnancy.amio.notification.notifiers.android;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Date;

import eu.telecomnancy.amio.MainActivity;
import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.iotlab.models.Mote;
import eu.telecomnancy.amio.notification.Constants;
import eu.telecomnancy.amio.notification.annotations.EventNotifier;
import eu.telecomnancy.amio.notification.contexts.NotificationContext;
import eu.telecomnancy.amio.notification.flags.NotificationType;
import eu.telecomnancy.amio.notification.notifiers.INotifier;

/**
 * Define a INotifier able to propagate a notification through Android's push notifications
 *
 * @see INotifier
 * @see eu.telecomnancy.amio.notification.flags.NotificationType
 */
@EventNotifier(target = NotificationType.ANDROID)
public class PushNotifier extends AndroidNotifier {

    /**
     * Android logging tag for this class
     */
    private static final String TAG = PushNotifier.class.getName();

    /**
     * Create a new notifier
     *
     * @param payload Notification payload to be later propagated
     */
    public PushNotifier(NotificationContext payload) {
        super(payload);
    }

    /**
     * Create the notification from the current context
     *
     * @param source Mote from which the notification has been emitted
     * @return The build notification
     */
    private Notification createNotification(Mote source) {
        // Retrieve the Android context from the notification context
        Context androidContext = payload.eventContext.pollingContext.androidContext;

        // Retrieve the notification content
        String title = androidContext.getString(R.string.notification_title);
        String content = androidContext.getString(R.string.notification_content);

        // Format the content
        content = String.format(content, source.getName());

        // Create an intent to open the main activity of the application when the user tap
        // on the notification
        Intent intent = new Intent(androidContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(androidContext, 0, intent, 0);

        // Create the notification from the prepared details
        return new NotificationCompat.Builder(androidContext, Constants.Notification.CHANNEL_ID)
                // Set core content
                .setSmallIcon(R.drawable.light_on)
                .setContentTitle(title)
                .setContentText(content)
                .setVibrate(Constants.Notification.VIBRATION_PATTERN)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(content))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                // Automatically removes the notification when the user taps the notification
                .setAutoCancel(true)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendNotification(Mote source) {
        Notification notification = createNotification(source);
        int notificationId = (int) new Date().getTime();

        NotificationManagerCompat.from(
                        payload.eventContext.pollingContext.androidContext)
                .notify(notificationId, notification);

        Log.d(TAG, "Push notification of id " + notificationId + " sent: " + notification);
    }

}
