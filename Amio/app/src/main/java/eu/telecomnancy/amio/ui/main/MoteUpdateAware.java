package eu.telecomnancy.amio.ui.main;

import java.util.List;

import eu.telecomnancy.amio.iotlab.entities.Mote;

public interface MoteUpdateAware {
    void onMotesUpdate(List<Mote> motes);
}
