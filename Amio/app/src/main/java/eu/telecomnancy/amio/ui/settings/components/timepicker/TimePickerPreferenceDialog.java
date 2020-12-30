package eu.telecomnancy.amio.ui.settings.components.timepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import androidx.preference.PreferenceDialogFragmentCompat;

import java.util.Calendar;

/**
 * TODO: add documentation
 */
public class TimePickerPreferenceDialog extends PreferenceDialogFragmentCompat {

    /**
     * TODO: add documentation
     */
    private TimePicker timePicker;

    /**
     * TODO: add documentation
     */
    private TimePickerPreference timePickerPreference;

    /**
     * TODO: add documentation
     *
     * @param key
     * @return
     */
    public static TimePickerPreferenceDialog newInstance(String key) {
        TimePickerPreferenceDialog fragment = new TimePickerPreferenceDialog();

        Bundle bundle = new Bundle();
        bundle.putString(ARG_KEY, key);

        fragment.setArguments(bundle);

        return fragment;
    }

    /**
     * TODO: add documentation
     *
     * @param view
     */
    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        timePickerPreference = ((TimePickerPreference) (this.getPreference()));

        // FIXME: extract method ? e.g.: private void configureTimePickerForCurrentTime()
        String time = timePickerPreference.getPersistedTime();
        long timestamp = TimePickerPreference.timeToTimestamp(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);

        // FIXME: move this to onCreateDialogView ?
        timePicker.setIs24HourView(true);

        timePicker.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setMinute(calendar.get(Calendar.MINUTE));
    }

    /**
     * TODO: add documentation
     *
     * @param context
     * @return
     */
    @Override
    protected View onCreateDialogView(Context context) {
        timePicker = new TimePicker(context);
        return timePicker;
    }

    /**
     * TODO: add documentation
     *
     * @param positiveResult
     */
    @SuppressLint("DefaultLocale")
    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (!positiveResult) {
            return;
        }

        // FIXME: extract a method ? e.g.: private String getFormattedTime() ?
        String minutes = timePicker.getMinute() / 10 == 0
                ? String.format("0%d", timePicker.getMinute())
                : String.valueOf(timePicker.getMinute());

        String formattedTime = String.format("%d:%s", timePicker.getHour(), minutes);

        timePickerPreference.persistTime(formattedTime);
    }

}
