package eu.telecomnancy.amio.notification.conditions.motes;

import eu.telecomnancy.amio.iotlab.entities.Mote;
import eu.telecomnancy.amio.iotlab.entities.collections.IMoteCollection;
import eu.telecomnancy.amio.notification.Constants;

/**
 * Condition to check if any light has been recently turned on
 */
public class IsAnyNewLightOn extends MotesCondition {

    /**
     * Create the condition
     *
     * @param motes Collection of motes on which running the assertion
     */
    public IsAnyNewLightOn(IMoteCollection motes) {
        super(motes);
    }

    @Override
    public boolean evaluate() {
        return _motes.toList()
                .stream()
                .anyMatch(Mote::isRoomLightened);
    }

}
