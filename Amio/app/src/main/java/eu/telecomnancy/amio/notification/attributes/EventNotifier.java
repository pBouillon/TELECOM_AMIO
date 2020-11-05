package eu.telecomnancy.amio.notification.attributes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import eu.telecomnancy.amio.notification.flags.NotificationType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EventNotifier {

    NotificationType target() default NotificationType.NONE;

}
