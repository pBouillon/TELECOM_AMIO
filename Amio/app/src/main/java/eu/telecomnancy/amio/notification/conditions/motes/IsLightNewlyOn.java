package eu.telecomnancy.amio.notification.conditions.motes;

import eu.telecomnancy.amio.iotlab.models.ConsecutiveMoteMeasuresPair;

/**
 * Condition to check if any light has been recently turned on
 */
public class IsLightNewlyOn extends MotesCondition {

    /**
     * Collection of the new motes and their previous retrieved values
     */
    private final ConsecutiveMoteMeasuresPair _comparedRecord;

    /**
     * Create the condition
     *
     * @param comparedRecords Collection of the new motes and their previous value retrieved
     */
    public IsLightNewlyOn(ConsecutiveMoteMeasuresPair comparedRecords) {
        _comparedRecord = comparedRecords;
    }

    @Override
    public boolean evaluate() {
        return _comparedRecord.isMoteRoomJustRecentlyLightened();
    }

}
