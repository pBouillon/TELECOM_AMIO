package eu.telecomnancy.amio.notification.rules;

import android.util.Log;

import org.jeasy.rules.annotation.Rule;

import java.util.stream.Stream;

import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.notification.conditions.ICondition;
import eu.telecomnancy.amio.notification.conditions.motes.IsLightNewlyOn;
import eu.telecomnancy.amio.notification.conditions.time.IsNotWeekEnd;
import eu.telecomnancy.amio.notification.conditions.time.IsTimeWithinInterval;
import eu.telecomnancy.amio.notification.contexts.EventContext;
import eu.telecomnancy.amio.notification.flags.NotificationType;
import eu.telecomnancy.amio.ui.settings.components.timepicker.TimePickerPreference;

/**
 * Rule to be activated when a new light is turned on on the week's evening
 */
@Rule(name = "New light on week's evening rule",
        description = "When a new light is detected on the week's evening, fire the event")
public class NewLightOnWeekEveningRule extends RuleBase {

    /**
     * Android logging tag for this class
     */
    private static final String TAG = NewLightOnWeekEveningRule.class.getName();

    @Override
    public NotificationType getNotificationTargets() {
        return NotificationType.ANDROID;
    }

    @Override
    public boolean isActiveWhen(EventContext context) {
        long currentTime = context.currentTime;

        String startTime = getPreferenceValue(context.pollingContext.androidContext, R.string.start_time_weekend_mail_notification_key, R.string.default_start_time_weekend_mail_notification);
        String endTime = getPreferenceValue(context.pollingContext.androidContext, R.string.end_time_weekend_mail_notification_key, R.string.default_end_time_weekend_mail_notification);
        long startTimestamp = TimePickerPreference.timeToTimestamp(startTime);
        long endTimestamp = TimePickerPreference.timeToTimestamp(endTime);

        boolean isActive = Stream.of(
                new IsLightNewlyOn(context.consecutiveMoteMeasuresPair),
                new IsNotWeekEnd(currentTime),
                new IsTimeWithinInterval(currentTime, startTimestamp, endTimestamp))
                .allMatch(ICondition::evaluate);

        Log.d(TAG, "Rule evaluated to " + isActive + " for the mote "
                + context.consecutiveMoteMeasuresPair.mostRecent.getName());

        return isActive;
    }

}
