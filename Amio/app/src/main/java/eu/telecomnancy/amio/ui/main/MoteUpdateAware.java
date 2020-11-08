package eu.telecomnancy.amio.ui.main;

import java.util.List;

import eu.telecomnancy.amio.iotlab.entities.Mote;

/**
 * TODO
 */
public interface MoteUpdateAware {

    /**
     * TODO
     */
    void onMotesUpdate(List<Mote> motes);

}
