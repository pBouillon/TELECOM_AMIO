package eu.telecomnancy.amio.notification.notifiers;

import eu.telecomnancy.amio.notification.NotificationContext;

public abstract class NotifierBase implements INotifier {

    protected final NotificationContext payload;

    protected NotifierBase(NotificationContext payload) {
        this.payload = payload;
    }

}
