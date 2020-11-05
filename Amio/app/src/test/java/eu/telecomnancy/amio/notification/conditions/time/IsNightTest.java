package eu.telecomnancy.amio.notification.conditions.time;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Calendar;

import eu.telecomnancy.amio.notification.conditions.ICondition;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit testing suite for the condition IsAnyNewLightOn
 * @see IsNightTest
 */
public class IsNightTest {

    /**
     * Ensure that the condition is not met when the current hour is not anymore in the night
     * @param hour Hour to be tested
     */
    @ParameterizedTest(name = "{0} hour(s) does not met the condition")
    @MethodSource(
            "eu.telecomnancy.amio.utils.providers.time.NightProvider#hoursOfTheDayAfterTheNight")
    public void evaluate_isFalseWhenAfterTheNight(int hour) {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);

        long time = calendar.getTimeInMillis();

        ICondition condition = new IsNight(time);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assertFalse(isConditionMet);
    }

    /**
     * Ensure that the condition is met when the current hour is in the night
     * @param hour Hour to be tested
     */
    @ParameterizedTest(name = "{0} hour(s) does not met the condition")
    @MethodSource(
            "eu.telecomnancy.amio.utils.providers.time.NightProvider#hoursOfTheDayForTheNight")
    public void evaluate_isTrueWhenDuringTheNight(int hour) {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);

        long time = calendar.getTimeInMillis();

        ICondition condition = new IsNight(time);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assertTrue(isConditionMet);
    }

}
