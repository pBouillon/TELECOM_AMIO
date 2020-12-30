package eu.telecomnancy.amio.ui.settings.components.timepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import androidx.preference.PreferenceDialogFragmentCompat;

import java.util.Calendar;

public class TimePickerPreferenceDialog extends PreferenceDialogFragmentCompat {


    private TimePicker timePicker;
    private TimePickerPreference preference;

    public static TimePickerPreferenceDialog newInstance(String key) {
        TimePickerPreferenceDialog fragment = new TimePickerPreferenceDialog();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_KEY, key);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View onCreateDialogView(Context context) {
        timePicker = new TimePicker(context);
        return timePicker;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        preference = ((TimePickerPreference) (this.getPreference()));
        String time = preference.getPersistedTime();
        long timestamp = TimePickerPreference.timeToTimestamp(time);
        timePicker.setIs24HourView(true);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        timePicker.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setMinute(calendar.get(Calendar.MINUTE));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            String minutes = timePicker.getMinute() / 10 == 0 ? String.format("0%d", timePicker.getMinute()) : String.valueOf(timePicker.getMinute());
            preference.persistTime(String.format("%d:%s", timePicker.getHour(), minutes));
        }
    }
}
