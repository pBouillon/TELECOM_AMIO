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

    /**
     * Used when no default value is set
     */
    // FIXME: find a more self-explanatory name ?
    private String value = DEFAULT_TIME;

    /**
     * TODO
     *
     * @param context
     * @param attrs
     */
    public TimePickerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Get saved preference value
     *
     * @return TODO
     */
    public String getPersistedTime() {
        return getPersistedString(value);
    }

    /**
     * Save preference
     *
     * @param time TODO
     */
    public void persistTime(String time) {
        this.persistString(time);
        value = time;
        updateSummary();
    }

    /**
     * TODO
     *
     * @param a
     * @param index
     * @return
     */
    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    /**
     * TODO
     *
     * @param defaultValue
     */
    @Override
    protected void onSetInitialValue(@Nullable Object defaultValue) {
        boolean isInitialTimeSet = getPersistedString("").isEmpty();

        String initialValue = isInitialTimeSet
                ? (String) defaultValue
                : getPersistedString(value);

        persistTime(initialValue);
    }

    /**
     * TODO
     *
     * @param time
     * @return
     */
    public static long timeToTimestamp(String time) {
        // FIXME: create a constant for the ":"
        String[] tab = time.split(":");
        if (tab.length != 2) {
            throw new DateTimeException("Time string must be %d:%d");
        }

        int hours = Integer.parseInt(tab[0]);
        int minutes = Integer.parseInt(tab[1]);

        // FIXME: extract a method ? e.g.: private static long getTimestampFromTime(int hours, int minutes)
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);

        return calendar.getTimeInMillis();
    }

    /**
     * TODO
     */
    private void updateSummary() {
        this.setSummary(getPersistedTime());
    }

}
