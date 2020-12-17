package eu.telecomnancy.amio.notification.notifiers;

import eu.telecomnancy.amio.iotlab.models.Mote;

/**
 * Define a component able to notify of an event
 */
public interface INotifier {

    /**
     * Send the notification held by the notifier
     *
     * @param source Mote from which the alert has been emitted
     */
    void sendNotification(Mote source);

}
