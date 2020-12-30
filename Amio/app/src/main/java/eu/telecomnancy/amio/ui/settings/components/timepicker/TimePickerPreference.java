package eu.telecomnancy.amio.ui.settings.components.timepicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.preference.DialogPreference;

import java.time.DateTimeException;
import java.util.Calendar;

/**
 * This class is used in our preference where user can pick a time for notifications to appear
 * Specifically, this class is responsible for saving/retrieving preference data
 */
public class TimePickerPreference extends DialogPreference {

    /**
     * Default time used for the {@link android.widget.TimePicker}
     */
    private static final String DEFAULT_TIME = "00:00";
    public static final String TIME_SEPARATOR = ":";

    /**
     * Used when no default value is set
     */
    private String currentSelectedTime = DEFAULT_TIME;

    /**
     * Default constructor matching the parent constructor
     *
     * @param context {@see DialogPreference}
     * @param attrs   {@see DialogPreference}
     */
    public TimePickerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Convert a time string into a timestamp
     *
     * @param time time string to convert
     * @return a timestamp that correspond to the time (use only for Hour and Minutes)
     */
    public static long timeToTimestamp(String time) {
        String[] tab = time.split(TIME_SEPARATOR);
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

    /**
     * Get saved preference value
     *
     * @return The persisted time
     */
    public String getPersistedTime() {
        return getPersistedString(currentSelectedTime);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    /**
     * Save preference
     *
     * @param time time to save in the preference manager
     */
    public void persistTime(String time) {
        this.persistString(time);
        currentSelectedTime = time;
        updateSummary();
    }

    @Override
    protected void onSetInitialValue(@Nullable Object defaultValue) {
        boolean isInitialTimeSet = getPersistedString("").isEmpty();

        String initialValue = isInitialTimeSet
                ? (String) defaultValue
                : getPersistedString(currentSelectedTime);

        persistTime(initialValue);
    }



    /**
     * TODO
     */
    private void updateSummary() {
        this.setSummary(getPersistedTime());
    }

}
