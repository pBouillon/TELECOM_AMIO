package eu.telecomnancy.amio.notification.conditions.sensors;

import eu.telecomnancy.amio.notification.conditions.ICondition;

/**
 * Condition to check if a light has been recently turned on
 */
public class IsNewLightOn implements ICondition {

    @Override
    public boolean evaluate() {
        // TODO: keep track of devices turned on and if any changes has occurred in their state
        return true;
    }

}
