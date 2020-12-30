package eu.telecomnancy.amio.ui.settings.components.timepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import androidx.preference.PreferenceDialogFragmentCompat;

import java.util.Calendar;

/**
 * This class represent the UI of the time picker dialog. {@see TimePickerPreference}
 */
public class TimePickerPreferenceDialog extends PreferenceDialogFragmentCompat {

    /**
     * The time picker shown in the dialog
     */
    private TimePicker timePicker;

    /**
     * The preference storage logic of this component
     */
    private TimePickerPreference timePickerPreference;

    /**
     * Used by the fragment manager to create the UI
     *
     * @param key key of the related parameter
     * @return a new instance of this fragment
     */
    public static TimePickerPreferenceDialog newInstance(String key) {
        TimePickerPreferenceDialog fragment = new TimePickerPreferenceDialog();

        Bundle bundle = new Bundle();
        bundle.putString(ARG_KEY, key);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        timePickerPreference = ((TimePickerPreference) (this.getPreference()));

        String time = timePickerPreference.getPersistedTime();
        long timestamp = TimePickerPreference.timeToTimestamp(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);

        timePicker.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setMinute(calendar.get(Calendar.MINUTE));
    }

    @Override
    protected View onCreateDialogView(Context context) {
        timePicker = new TimePicker(context);
        timePicker.setIs24HourView(true);
        return timePicker;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (!positiveResult) {
            return;
        }

        String minutes = timePicker.getMinute() / 10 == 0
                ? String.format("0%d", timePicker.getMinute())
                : String.valueOf(timePicker.getMinute());

        String formattedTime = String.format("%d:%s", timePicker.getHour(), minutes);

        timePickerPreference.persistTime(formattedTime);
    }

}
