package eu.telecomnancy.amio.ui.settings.components.timepicker;

import android.annotation.SuppressLint;

import java.time.DateTimeException;

public abstract class Util {
    @SuppressLint("DefaultLocale")
    public static String getTimeFromMinutesFromMidnight(int minutesFromMidnight) {
        String minutes = minutesFromMidnight % 60 / 10 == 0 ? String.format("0%d", minutesFromMidnight % 60) : String.valueOf(minutesFromMidnight % 60);
        return String.format("%d:%s", minutesFromMidnight / 60, minutes);
    }

    public static int getMinutesFromMidnightFromTime(String time) {
        String[] tab = time.split(":");
        if (tab.length != 2) {
            throw new DateTimeException("Time string must be %d:%d");
        }
        int hours = Integer.parseInt(tab[0]);
        int minutes = Integer.parseInt(tab[1]);
        return hours * 60 + minutes;
    }
}
