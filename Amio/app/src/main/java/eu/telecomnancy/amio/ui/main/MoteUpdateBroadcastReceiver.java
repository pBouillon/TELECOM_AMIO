package eu.telecomnancy.amio.ui.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.List;

import eu.telecomnancy.amio.iotlab.entities.Mote;
import eu.telecomnancy.amio.polling.Constants;

/**
 * TODO doc
 */
public class MoteUpdateBroadcastReceiver extends BroadcastReceiver {

    /**
     * TODO doc
     */
    private final IMoteUpdateAware _moteUpdateAware;

    /**
     * TODO doc
     */
    public MoteUpdateBroadcastReceiver(IMoteUpdateAware moteUpdateAware) {
        _moteUpdateAware = moteUpdateAware;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // noinspection unchecked
        List<Mote> motes
                = (List<Mote>) intent.getBundleExtra(Constants.Broadcast.BUNDLE_IDENTIFIER)
                .getSerializable(Constants.Broadcast.IDENTIFIER);

        _moteUpdateAware.onMotesUpdate(motes);
    }

}
