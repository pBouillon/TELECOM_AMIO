package eu.telecomnancy.amio.notification.dispatchers;

import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import eu.telecomnancy.amio.notification.NotificationContext;
import eu.telecomnancy.amio.notification.annotations.EventNotifier;
import eu.telecomnancy.amio.notification.notifiers.INotifier;
import eu.telecomnancy.amio.notification.notifiers.NotifierBase;

/**
 * Dispatch an incoming notification to the appropriates notifiers
 */
public final class NotificationDispatcher {

    /**
     * Packages in which searching for the notifiers
     */
    private static final String TARGET_NAMESPACE = "eu.telecomnancy.amio";

    /**
     * Given a context, send the notification to all the notifiers supporting its type
     *
     * @param context Context of the notification
     * @see INotifier
     * @see NotificationContext
     * @see eu.telecomnancy.amio.notification.flags.NotificationType
     */
    public static void sendNotificationsFrom(NotificationContext context) {
        // Create the reflexion utility
        Reflections reflections = new Reflections(TARGET_NAMESPACE);

        // Send the notification to all notifiers
        reflections
                // Retrieve all types annotated with the EventNotifier annotation
                .getTypesAnnotatedWith(EventNotifier.class)
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
     * @return An instance of the associated INotifier type; null if any error occured
     */
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
