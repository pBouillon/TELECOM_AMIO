package eu.telecomnancy.amio.notification.notifiers;

/**
 * Define a component able to notify of an event
 */
public interface INotifier {

    /**
     * Send the notification held by the notifier
     */
    void sendNotification();

}
