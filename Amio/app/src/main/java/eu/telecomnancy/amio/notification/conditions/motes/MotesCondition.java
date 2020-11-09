package eu.telecomnancy.amio.notification.conditions.motes;

import eu.telecomnancy.amio.iotlab.entities.collections.IMoteCollection;
import eu.telecomnancy.amio.notification.conditions.ICondition;

/**
 * Condition template for all condition that will depend on a collection of fetched motes
 */
public abstract class MotesCondition implements ICondition {

    /**
     * Collection of motes on which running the assertion
     *
     * @see IMoteCollection
     */
    protected final IMoteCollection _motes;

    /**
     * Create the condition
     *
     * @param motes Collection of motes on which running the assertion
     */
    protected MotesCondition(IMoteCollection motes) {
        _motes = motes;
    }

}
