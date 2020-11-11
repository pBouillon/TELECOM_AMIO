package eu.telecomnancy.amio.ui.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.List;

import eu.telecomnancy.amio.iotlab.models.Mote;
import eu.telecomnancy.amio.polling.Constants;

/**
 * Broadcast receiver designed to handle any update in the mote list dispatched by the Polling service
 *
 * @see eu.telecomnancy.amio.polling.PollingTaskBase
 */
public class MoteUpdateBroadcastReceiver extends BroadcastReceiver {

    /**
     * Will trigger its callback whenever new motes are received
     */
    private final IMoteUpdateWatcher _moteUpdateAware;

    /**
     * Default constructor for a MoteUpdateBroadcastReceiver
     *
     * @param moteUpdateAware set moteUpdateAware
     */
    public MoteUpdateBroadcastReceiver(IMoteUpdateWatcher moteUpdateAware) {
        _moteUpdateAware = moteUpdateAware;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // noinspection unchecked
        List<Mote> motes
                = (List<Mote>) intent
                .getBundleExtra(Constants.Broadcast.BUNDLE_IDENTIFIER)
                .getSerializable(Constants.Broadcast.IDENTIFIER);

        // Trigger the custom function on the object that need an update
        _moteUpdateAware.onMotesUpdate(motes);
    }

}
