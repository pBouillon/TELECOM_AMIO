package eu.telecomnancy.amio.ui.settings.components.timepicker;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

import androidx.preference.PreferenceDialogFragmentCompat;

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
        int minAfterMidnight = preference.getPersistedMinutesFromMidnight();
        timePicker.setIs24HourView(true);
        Log.e("", minAfterMidnight + "");
        timePicker.setHour(minAfterMidnight / 60);
        timePicker.setMinute(minAfterMidnight % 60);
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            int minAfterMidnight = timePicker.getHour() * 60 + timePicker.getMinute();
            preference.persistMinutesFromMidnight(minAfterMidnight);
        }
    }
}
