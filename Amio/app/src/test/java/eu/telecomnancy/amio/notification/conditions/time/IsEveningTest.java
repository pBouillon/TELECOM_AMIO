package eu.telecomnancy.amio.notification.conditions.time;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Calendar;

import eu.telecomnancy.amio.notification.conditions.ICondition;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit testing suite for the condition IsAnyNewLightOn
 * @see IsEvening
 */
public class IsEveningTest {

    /**
     * Ensure that the condition is not met when the current hour is not anymore in the evening
     * @param hour Hour to be tested
     */
    @ParameterizedTest(name = "{0} hour(s) does not met the condition")
    @MethodSource(
            "eu.telecomnancy.amio.utils.providers.time.EveningProvider#hoursOfTheDayAfterEvening")
    public void evaluate_isFalseWhenAfterEvening(int hour) {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);

        long time = calendar.getTimeInMillis();

        ICondition condition = new IsEvening(time);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assertFalse(isConditionMet);
    }

    /**
     * Ensure that the condition is not met when the current hour is not yet in the evening
     * @param hour Hour to be tested
     */
    @ParameterizedTest(name = "{0} hour(s) does not met the condition")
    @MethodSource(
            "eu.telecomnancy.amio.utils.providers.time.EveningProvider#hoursOfTheDayBeforeEvening")
    public void evaluate_isFalseWhenBeforeEvening(int hour) {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);

        long time = calendar.getTimeInMillis();

        ICondition condition = new IsEvening(time);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assertFalse(isConditionMet);
    }

}
