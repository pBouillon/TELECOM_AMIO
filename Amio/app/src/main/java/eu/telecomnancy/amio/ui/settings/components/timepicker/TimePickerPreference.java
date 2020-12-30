package eu.telecomnancy.amio.ui.settings.components.timepicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.preference.DialogPreference;

import java.time.DateTimeException;
import java.util.Calendar;

// This class is used in our preference where user can pick a time for notifications to appear.
// Specifically, this class is responsible for saving/retrieving preference data.
public class TimePickerPreference extends DialogPreference {
    // Used when no default value is set
    private String value = "00:00";

    public TimePickerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static long timeToTimestamp(String time) {
        String[] tab = time.split(":");
        if (tab.length != 2) {
            throw new DateTimeException("Time string must be %d:%d");
        }
        int hours = Integer.parseInt(tab[0]);
        int minutes = Integer.parseInt(tab[1]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        return calendar.getTimeInMillis();
    }

    // Get saved preference value
    public String getPersistedTime() {
        return this.getPersistedString(value);
    }

    // Save preference
    public void persistTime(String time) {
        this.persistString(time);
        value = time;
        updateSummary();
    }

    @Override
    protected void onSetInitialValue(@Nullable Object defaultValue) {
        persistTime(getPersistedString("").isEmpty() ? (String) defaultValue : getPersistedString(value));
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    private void updateSummary() {
        this.setSummary(getPersistedTime());
    }
}