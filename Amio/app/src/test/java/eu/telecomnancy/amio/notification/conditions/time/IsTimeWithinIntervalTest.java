package eu.telecomnancy.amio.notification.conditions.time;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Calendar;

import eu.telecomnancy.amio.notification.conditions.ICondition;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit testing suite for the condition IsWithinTimeInterval
 * @see IsTimeWithinInterval
 */
public class IsTimeWithinIntervalTest {

    /**
     * Ensure that the condition is not met when the current hour is not anymore in the evening
     * interval
     *
     * @param hour Hour to be tested
     */
    @ParameterizedTest(name = "{0} hour(s) does not met the condition")
    @MethodSource(
            "eu.telecomnancy.amio.utils.providers.time.TimeProvider#hoursOfTheDayAfterEvening")
    public void evaluate_isFalseWhenAfterEveningInterval(int hour) {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);

        long time = calendar.getTimeInMillis();

        calendar.set(Calendar.HOUR_OF_DAY + 1, hour);
        long startTime = calendar.getTimeInMillis();

        calendar.set(Calendar.HOUR_OF_DAY + 2, hour);
        long endTime = calendar.getTimeInMillis();

        ICondition condition = new IsTimeWithinInterval(time, startTime, endTime);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assertFalse(isConditionMet);
    }

    /**
     * Ensure that the condition is not met when the current hour is not anymore in the night
     * interval
     *
     * @param hour Hour to be tested
     */
    @ParameterizedTest(name = "{0} hour(s) does not met the condition")
    @MethodSource(
            "eu.telecomnancy.amio.utils.providers.time.TimeProvider#hoursOfTheDayAfterTheNight")
    public void evaluate_isFalseWhenAfterNightInterval(int hour) {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);

        long time = calendar.getTimeInMillis();

        calendar.set(Calendar.HOUR_OF_DAY + 1, hour);
        long startTime = calendar.getTimeInMillis();

        calendar.set(Calendar.HOUR_OF_DAY + 2, hour);
        long endTime = calendar.getTimeInMillis();

        ICondition condition = new IsTimeWithinInterval(time, startTime, endTime);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assertFalse(isConditionMet);
    }

    /**
     * Ensure that the condition is not met when the current hour is not yet in the evening interval
     *
     * @param hour Hour to be tested
     */
    @ParameterizedTest(name = "{0} hour(s) does not met the condition")
    @MethodSource(
            "eu.telecomnancy.amio.utils.providers.time.TimeProvider#hoursOfTheDayBeforeEvening")
    public void evaluate_isFalseWhenBeforeEveningInterval(int hour) {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);

        long time = calendar.getTimeInMillis();

        calendar.set(Calendar.HOUR_OF_DAY - 2, hour);
        long startTime = calendar.getTimeInMillis();

        calendar.set(Calendar.HOUR_OF_DAY - 1, hour);
        long endTime = calendar.getTimeInMillis();

        ICondition condition = new IsTimeWithinInterval(time, startTime, endTime);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assertFalse(isConditionMet);
    }

    /**
     * Ensure that the condition is not met when the current hour is not yet in the night interval
     *
     * @param hour Hour to be tested
     */
    @ParameterizedTest(name = "{0} hour(s) does not met the condition")
    @MethodSource(
            "eu.telecomnancy.amio.utils.providers.time.TimeProvider#hoursOfTheDayForTheNight")
    public void evaluate_isFalseWhenBeforeNightInterval(int hour) {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);

        long time = calendar.getTimeInMillis();

        calendar.set(Calendar.HOUR_OF_DAY - 2, hour);
        long startTime = calendar.getTimeInMillis();

        calendar.set(Calendar.HOUR_OF_DAY - 1, hour);
        long endTime = calendar.getTimeInMillis();

        ICondition condition = new IsTimeWithinInterval(time, startTime, endTime);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assertFalse(isConditionMet);
    }

}
