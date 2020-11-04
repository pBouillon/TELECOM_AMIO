package eu.telecomnancy.amio.ui.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import eu.telecomnancy.amio.iotlab.entities.Mote;
import eu.telecomnancy.amio.polling.PollingService;

public class MoteUpdateBroadcastReciever extends BroadcastReceiver {
    private MoteUpdateAware mMoteUpdateAware;

    public MoteUpdateBroadcastReciever(MoteUpdateAware moteUpdateAware){
        mMoteUpdateAware = moteUpdateAware;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        List<Mote> motes = (List<Mote>) intent.getBundleExtra(PollingService.MOTES_BUNDLE_IDENTIFIER).getSerializable(PollingService.MOTES_EXTRA_IDENTIFIER);
        mMoteUpdateAware.onMotesUpdate(motes);
    }
}
