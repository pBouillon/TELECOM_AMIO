package eu.telecomnancy.amio.persistence.commons;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * Type converters for date manipulation
 *
 * From: https://developer.android.com/training/data-storage/room/referencing-data
 */
public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long timestamp) {
        return timestamp == null
                ? null
                : new Date(timestamp);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null
                ? null
                : date.getTime();
    }

}
