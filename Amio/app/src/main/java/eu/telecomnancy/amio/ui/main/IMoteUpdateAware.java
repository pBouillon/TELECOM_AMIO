package eu.telecomnancy.amio.ui.main;

import java.util.List;

import eu.telecomnancy.amio.iotlab.entities.Mote;

/**
 * TODO doc
 */
public interface IMoteUpdateAware {

    /**
     * TODO doc
     */
    void onMotesUpdate(List<Mote> motes);

}
