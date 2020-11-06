package eu.telecomnancy.amio.notification.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import eu.telecomnancy.amio.notification.flags.NotificationType;

/**
 * Custom annotation to specify the type of notification handled by an INotifier
 *
 * @see eu.telecomnancy.amio.notification.notifiers.INotifier
 * @see NotificationType
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EventNotifier {

    /**
     * Type of the notification the class tagged with this annotation can handle
     *
     * @return The handled type
     */
    NotificationType target() default NotificationType.NONE;

}
