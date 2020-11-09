package eu.telecomnancy.amio.notification.flags;

/**
 * All type of channels on which a notification can be propagated
 */
public enum NotificationType {

    /**
     * Notification to be propagated on the current Android device
     */
    ANDROID,

    /**
     * Notification to be propagated by mail
     */
    EMAIL,

    /**
     * Notification not to be propagated
     */
    NONE,

}
