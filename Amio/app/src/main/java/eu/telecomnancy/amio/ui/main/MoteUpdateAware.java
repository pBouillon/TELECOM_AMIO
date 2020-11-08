package eu.telecomnancy.amio.ui.main;

import java.util.List;

import eu.telecomnancy.amio.iotlab.entities.Mote;

/**
 * TODO doc
 */
public interface MoteUpdateAware {

    /**
     * TODO doc
     */
    void onMotesUpdate(List<Mote> motes);

}
