package eu.telecomnancy.amio.utils.providers.time;

import java.util.Calendar;
import java.util.stream.IntStream;

import eu.telecomnancy.amio.notification.Constants;

/**
 * Provider for time spans and specific times related to the evening
 * @see Constants
 *
 * Those methods are called at runtime in the test cases
 * @see eu.telecomnancy.amio.notification.conditions.time.IsNotWeekEndTest
 */
@SuppressWarnings("unused")
public final class DayProvider {

    /**
     * Retrieve all days of the week, excluding the week-end
     * @return An IntStream of the days indexes it corresponds to
     */
    public static IntStream daysOfTheWeek() {
        return IntStream.range(Calendar.MONDAY, Calendar.SATURDAY);
    }

    /**
     * Retrieve all days of the week-end
     * @return An IntStream of the days indexes it corresponds to
     */
    public static IntStream daysOfTheWeekEnd() {
        return IntStream.of(Calendar.SATURDAY, Calendar.SUNDAY);
    }

}
