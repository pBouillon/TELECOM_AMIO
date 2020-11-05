package eu.telecomnancy.amio.notification.notifiers;

import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import eu.telecomnancy.amio.notification.NotificationContext;
import eu.telecomnancy.amio.notification.annotations.EventNotifier;

public final class NotificationDispatcher {

    private static final String TARGET_NAMESPACE = "eu.telecomnancy.amio.notification.notifiers";

    public static void sendNotificationsFrom(NotificationContext context) {
        Reflections reflections = new Reflections(TARGET_NAMESPACE);

        Set<Class<?>> scannedNotifierClasses =
                reflections.getTypesAnnotatedWith(EventNotifier.class);

        List<INotifier> scannedNotifiers = scannedNotifierClasses
                .stream()
                .map(type -> invokeINotifierFromType(type, context))
                .collect(Collectors.toList());

        scannedNotifiers
                .stream()
                .filter(notifier ->
                    notifier.getClass()
                            .getAnnotation(EventNotifier.class)
                            .target()
                            == context.type)
                .forEach(INotifier::sendNotification);
    }

    @Nullable
    private static INotifier invokeINotifierFromType(Class<?> type, NotificationContext context) {
        INotifier notifier = null;
        Constructor<NotifierBase> constructor ;

        try {
            constructor = (Constructor<NotifierBase>) type.getConstructor(context.getClass());
            notifier = constructor.newInstance(context);
        } catch (NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException
                | InstantiationException e) {
            e.printStackTrace();
        }

        return notifier;
    }

}
