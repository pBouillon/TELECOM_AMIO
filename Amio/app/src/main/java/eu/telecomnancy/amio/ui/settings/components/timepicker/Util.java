package eu.telecomnancy.amio.ui.settings.components.timepicker;

import java.util.Calendar;

public abstract class Util {


    public static boolean isTimeBetween2MinuteFromMidnight(int timeInMillisecond, int startMinutesFromMidnight, int endMinutesFromMidnight) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillisecond);
        int currentTime = calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
        return (startMinutesFromMidnight < currentTime && currentTime < endMinutesFromMidnight) ||
                (startMinutesFromMidnight > endMinutesFromMidnight && (startMinutesFromMidnight < currentTime || currentTime < endMinutesFromMidnight));
    }
}
