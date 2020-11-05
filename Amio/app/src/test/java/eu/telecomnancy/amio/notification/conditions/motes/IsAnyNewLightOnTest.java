package eu.telecomnancy.amio.notification.conditions.motes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.Date;

import eu.telecomnancy.amio.iotlab.dto.MoteDto;
import eu.telecomnancy.amio.iotlab.entities.collections.IMoteCollection;
import eu.telecomnancy.amio.notification.Constants;
import eu.telecomnancy.amio.notification.conditions.ICondition;
import eu.telecomnancy.amio.utils.factories.MoteCollectionFactory;

/**
 * Unit testing suite for the condition IsAnyNewLightOn
 * @see IsAnyNewLightOn
 */
public class IsAnyNewLightOnTest {

    /**
     * Factory used to generate fixture data
     */
    private final MoteCollectionFactory _factory = new MoteCollectionFactory();

    /**
     * Ensure that the condition is not met when there isn't any brightness value of any mote above
     * the specified threshold
     */
    @Test
    public void evaluate_isFalseWhenTheThresholdIsNotMet() {
        // Arrange
        IMoteCollection motes = _factory.generateMoteCollection(
                Constants.Thresholds.Lux.LIGHTED_ROOM - 1);

        ICondition condition = new IsAnyNewLightOn(motes);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assertFalse(isConditionMet);
    }

    /**
     * Ensure that the condition is met when a mote has its brightness above the specified threshold
     */
    @Test
    public void evaluate_isTrueWhenTheThresholdIsMetByAMote() {
        // Arrange
        IMoteCollection motes = _factory.generateMoteCollection(
                Constants.Thresholds.Lux.LIGHTED_ROOM - 1);

        // Create the lightened mote
        MoteDto lightenedRoomMote = new MoteDto();
        lightenedRoomMote.mote = String.valueOf(new Date().getTime());
        lightenedRoomMote.label = eu.telecomnancy.amio.iotlab.Constants.Labels.BRIGHTNESS;
        lightenedRoomMote.value = Constants.Thresholds.Lux.LIGHTED_ROOM + 1;

        // Add the lightened mote to the collection of motes
        motes.addAndMergeFromDto(lightenedRoomMote);

        ICondition condition = new IsAnyNewLightOn(motes);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assumeTrue(!motes.isEmpty());
        assertTrue(isConditionMet);
    }

    /**
     * Ensure that the condition is met when several or all motes have their brightness above
     * the specified threshold
     */
    @Test
    public void evaluate_isTrueWhenTheThresholdIsMetBySeveralMotes() {
        // Arrange
        IMoteCollection motes = _factory.generateMoteCollection(
                Constants.Thresholds.Lux.LIGHTED_ROOM,
                Constants.Thresholds.Lux.LIGHTED_ROOM + 1);

        ICondition condition = new IsAnyNewLightOn(motes);

        // Act
        boolean isConditionMet = condition.evaluate();

        // Assert
        assumeTrue(!motes.isEmpty());
        assertTrue(isConditionMet);
    }

}
