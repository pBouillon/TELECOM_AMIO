package eu.telecomnancy.amio.notification.conditions.motes;

import java.util.List;

import eu.telecomnancy.amio.iotlab.models.ConsecutiveMoteMeasuresPair;

/**
 * Condition to check if any light has been recently turned on
 */
public class IsAnyNewLightOn extends MotesCondition {

    /**
     * Collection of the new motes and their previous retrieved values
     */
    private final List<ConsecutiveMoteMeasuresPair> _comparedRecords;

    /**
     * Create the condition
     *
     * @param comparedRecords Collection of the new motes and their previous value retrieved
     */
    public IsAnyNewLightOn(List<ConsecutiveMoteMeasuresPair> comparedRecords) {
        _comparedRecords = comparedRecords;
    }

    @Override
    public boolean evaluate() {
        return _comparedRecords
                .stream()
                .anyMatch(ConsecutiveMoteMeasuresPair::isMoteRoomJustRecentlyLightened);
    }

}
