package eu.telecomnancy.amio.notification.dispatchers;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import eu.telecomnancy.amio.notification.contexts.NotificationContext;
import eu.telecomnancy.amio.notification.annotations.EventNotifier;
import eu.telecomnancy.amio.notification.notifiers.INotifier;
import eu.telecomnancy.amio.notification.notifiers.NotifierBase;
import eu.telecomnancy.amio.notification.notifiers.android.*;
import eu.telecomnancy.amio.notification.notifiers.email.*;

/**
 * Dispatch an incoming notification to the appropriates notifiers
 */
public final class NotificationDispatcher {

    /**
     * Available notifiers to be used to send appropriate notifications depending of the context
     *
     * @see INotifier
     * @see NotificationContext
     * @see eu.telecomnancy.amio.notification.flags.NotificationType
     */
    private static final List<Class<? extends INotifier>> notifierClasses = Arrays.asList(
            // Android notifiers
            PushNotifier.class,

            // Email notifiers
            SmtpNotifier.class
    );

    /**
     * Given a context, send the notification to all the notifiers supporting its type
     *
     * @param context Context of the notification
     * @see INotifier
     * @see NotificationContext
     * @see eu.telecomnancy.amio.notification.flags.NotificationType
     */
    public static void sendNotificationsFrom(NotificationContext context) {
        notifierClasses
                // Keep only the ones related to the context
                .stream()
                .filter(type -> type
                        .getAnnotation(EventNotifier.class)
                        .target()
                        == context.type)
                // Invoke a new instance of the INotifier type retrieved
                .map(type -> invokeINotifierFromType(type, context))
                .filter(Objects::nonNull)
                // Send the notification from all the matching types
                .forEach(INotifier::sendNotification);
    }

    /**
     * Attempt to create an instance of INotifier from the provided type and a given context
     *
     * @param type Derived of INotifier to instantiate
     * @param context Notification context to be provided in the constructor according to the base
     *                class
     * @return An instance of the associated INotifier type; null if any error occurred
     */
    @SuppressWarnings("unchecked")
    @Nullable
    private static INotifier invokeINotifierFromType(Class<?> type, NotificationContext context) {
        // Attempt to dynamic create an instance of the provided type
        try {
            // Retrieve the constructor that take a NotificationContext in parameter
            Constructor<NotifierBase> constructor =
                    (Constructor<NotifierBase>) type.getConstructor(context.getClass());
            // Create and return the new instance
            return constructor.newInstance(context);
        } catch (NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException
                | InstantiationException e) {
            e.printStackTrace();
        }

        // If any error occurred, return null
        return null;
    }

}
