package eu.telecomnancy.amio.notification.conditions.time;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Calendar;

import eu.telecomnancy.amio.notification.conditions.ICondition;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit testing suite for the condition IsNotWeekEnd
 * @see IsNotWeekEnd
 */
public class IsNotWeekEndTest {

    /**
     * Ensure that the condition is not met when the day is in the week-end
     * @param day Index of the day to be tested
     */
    @ParameterizedTest(name = "Day #{0} does met the condition")
    @MethodSource(
            "eu.telecomnancy.amio.utils.providers.time.DayProvider#daysOfTheWeekEnd")
    public void evaluate_isFalseWhenInTheWeekEnd(int day) {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, day);

        long time = calendar.getTimeInMillis();

        ICondition condition = new IsNotWeekEnd(time);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assertFalse(isConditionMet);
    }

    /**
     * Ensure that the condition is met when the day is not in the week-end
     * @param day Index of the day to be tested
     */
    @ParameterizedTest(name = "Day #{0} does met the condition")
    @MethodSource(
            "eu.telecomnancy.amio.utils.providers.time.DayProvider#daysOfTheWeek")
    public void evaluate_isTrueWhenNotTheWeekEnd(int day) {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, day);

        long time = calendar.getTimeInMillis();

        ICondition condition = new IsNotWeekEnd(time);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assertTrue(isConditionMet);
    }

}
