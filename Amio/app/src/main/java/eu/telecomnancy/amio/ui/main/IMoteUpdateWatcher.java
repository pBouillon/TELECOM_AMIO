package eu.telecomnancy.amio.ui.main;

import java.util.List;

import eu.telecomnancy.amio.iotlab.entities.Mote;

/**
 * Interface used by all object that need an update from the {@link MoteUpdateBroadcastReceiver}
 */
public interface IMoteUpdateWatcher {

    /**
     * Function triggered on each mote update
     * @param motes the new mote's list
     */
    void onMotesUpdate(List<Mote> motes);

}
