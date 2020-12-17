package eu.telecomnancy.amio.ui.settings.components.timepicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.preference.DialogPreference;

// This class is used in our preference where user can pick a time for notifications to appear.
// Specifically, this class is responsible for saving/retrieving preference data.
public class TimePickerPreference extends DialogPreference {
    // Mostly for default values
    // By default we want notification to appear at 9 AM each time.
    private int value = 0;

    public TimePickerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // Get saved preference value (in minutes from midnight, so 1 AM is represented as 1*60 here
    public int getPersistedMinutesFromMidnight() {
        return this.getPersistedInt(value);
    }

    // Save preference
    public void persistMinutesFromMidnight(int minutesFromMidnight) {
        this.persistInt(minutesFromMidnight);
        value = minutesFromMidnight;
        updateSummary();
    }

    @Override
    protected void onSetInitialValue(@Nullable Object defaultValue) {
        persistMinutesFromMidnight(getPersistedInt(-1) == -1 ? (int) defaultValue : getPersistedInt(value));
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return Util.getMinutesFromMidnightFromTime(a.getString(index));
    }

    private void updateSummary() {
        this.setSummary(Util.getTimeFromMinutesFromMidnight(getPersistedMinutesFromMidnight()));
    }


}