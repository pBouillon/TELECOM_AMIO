package eu.telecomnancy.amio.utils.providers.time;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import eu.telecomnancy.amio.notification.Constants;

/**
 * Provider for time spans and specific times related to the night
 * @see eu.telecomnancy.amio.notification.Constants
 *
 * Those methods are called at runtime in the test cases
 * @see eu.telecomnancy.amio.notification.conditions.time.IsNightTest
 */
@SuppressWarnings("unused")
public final class NightProvider {

    /**
     * Retrieve all hours of the day from the end of the night to the end of the day
     * @return An IntStream of the hours it corresponds to
     */
    public static IntStream hoursOfTheDayAfterTheNight() {
        return IntStream.range(Constants.TimeSpans.Night.END, Constants.TimeSpans.Night.BEGINNING);
    }

    /**
     * Retrieve all hours of the day from the beginning of the night to its end
     * @return An IntStream of the hours it corresponds to
     */
    public static IntStream hoursOfTheDayForTheNight() {
        return IntStream.concat(
                IntStream.range(0, Constants.TimeSpans.Night.END),
                IntStream.range(Constants.TimeSpans.Night.BEGINNING, 24)
        );
    }

}
