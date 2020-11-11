package eu.telecomnancy.amio.notification.conditions.motes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import eu.telecomnancy.amio.iotlab.models.ConsecutiveMoteMeasuresPair;
import eu.telecomnancy.amio.iotlab.models.Mote;
import eu.telecomnancy.amio.notification.Constants;
import eu.telecomnancy.amio.notification.conditions.ICondition;
import eu.telecomnancy.amio.utils.factories.MoteFactory;

/**
 * Unit testing suite for the condition IsAnyNewLightOn
 * @see IsAnyNewLightOn
 */
public class IsAnyNewLightOnTest {

    /**
     * Factory used to generate fixture data
     */
    private final MoteFactory _factory = new MoteFactory();

    /**
     * Ensure that the condition is not met when neither of the records are showing a
     * lightened room
     */
    @Test
    public void evaluate_isFalseWhenBothRecordsShowLightenedRoom() {
        // Arrange
        int brightRoom = Constants.Thresholds.Lux.LIGHTED_ROOM + 1;

        Mote oldest = _factory.generate(brightRoom, 1);
        Mote recent = _factory.generate(brightRoom, 2, oldest.getName());

        List<ConsecutiveMoteMeasuresPair> records = new ArrayList<>();
        records.add(new ConsecutiveMoteMeasuresPair(recent, oldest));

        ICondition condition = new IsAnyNewLightOn(records);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assertFalse(isConditionMet);
    }

    /**
     * Ensure that the condition is not met when both of the records are showing a
     * lightened room
     */
    @Test
    public void evaluate_isFalseWhenBothRecordsShowNotLightenedRoom() {
        // Arrange
        int darkRoom = Constants.Thresholds.Lux.LIGHTED_ROOM - 1;

        Mote oldest = _factory.generate(darkRoom, 1);
        Mote recent = _factory.generate(darkRoom, 2, oldest.getName());

        List<ConsecutiveMoteMeasuresPair> records = new ArrayList<>();
        records.add(new ConsecutiveMoteMeasuresPair(recent, oldest));

        ICondition condition = new IsAnyNewLightOn(records);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assertFalse(isConditionMet);
    }

    /**
     * Ensure that the condition is not met when the oldest record is showing a lightened room
     * but the most recent one does not
     */
    @Test
    public void evaluate_isFalseWhenRecordsAreShowingARoomRecentlyTurnedOff() {
        // Arrange
        int brightRoom = Constants.Thresholds.Lux.LIGHTED_ROOM + 1;
        int darkRoom = Constants.Thresholds.Lux.LIGHTED_ROOM - 1;

        Mote oldest = _factory.generate(brightRoom, 1);
        Mote recent = _factory.generate(darkRoom, 2, oldest.getName());

        List<ConsecutiveMoteMeasuresPair> records = new ArrayList<>();
        records.add(new ConsecutiveMoteMeasuresPair(recent, oldest));

        ICondition condition = new IsAnyNewLightOn(records);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assertFalse(isConditionMet);
    }

    /**
     * Ensure that the condition is met when the oldest record show a room that has not been
     * lightened but the most recent one does
     */
    @Test
    public void evaluate_isTrueWhenRecordsAreShowingARoomRecentlyLightened() {
        // Arrange
        int brightRoom = Constants.Thresholds.Lux.LIGHTED_ROOM + 1;
        int darkRoom = Constants.Thresholds.Lux.LIGHTED_ROOM - 1;

        Mote oldest = _factory.generate(darkRoom, 1);
        Mote recent = _factory.generate(brightRoom, 2, oldest.getName());

        List<ConsecutiveMoteMeasuresPair> records = new ArrayList<>();
        records.add(new ConsecutiveMoteMeasuresPair(recent, oldest));

        ICondition condition = new IsAnyNewLightOn(records);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assertTrue(isConditionMet);
    }


}
