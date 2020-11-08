package eu.telecomnancy.amio.utils.providers.time;

import java.util.stream.IntStream;

import eu.telecomnancy.amio.notification.Constants;

/**
 * Provider for time spans and specific times related to the evening
 * @see Constants
 *
 * Those methods are called at runtime in the test cases
 * @see eu.telecomnancy.amio.notification.conditions.time.IsEveningTest
 */
@SuppressWarnings("unused")
public final class EveningProvider {

    /**
     * Retrieve all hours of the day from the end of the evening to the end of the day
     * @return An IntStream of the hours it corresponds to
     */
    public static IntStream hoursOfTheDayAfterEvening() {
        return IntStream.range(Constants.TimeSpans.Evening.END, 24);
    }

    /**
     * Retrieve all hours of the day from the beginning of the day to its end
     * @return An IntStream of the hours it corresponds to
     */
    public static IntStream hoursOfTheDayBeforeEvening() {
        return IntStream.range(0, Constants.TimeSpans.Evening.BEGINNING);
    }

    /**
     * Retrieve all hours of the day from the beginning of the evening to its end
     * @return An IntStream of the hours it corresponds to
     */
    public static IntStream hoursOfTheDayForEvening() {
        return IntStream.range(
                Constants.TimeSpans.Evening.BEGINNING, Constants.TimeSpans.Evening.END);
    }

}
