package eu.telecomnancy.amio.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import eu.telecomnancy.amio.iotlab.entities.Mote;

/**
 * Using a ViewModel avoid the service to be stopped and restarted wen the device orientation change
 * (or any other thing that refresh the UI)
 */
public class MainViewModel extends ViewModel {

    /**
     * TODO doc
     */
    private final MutableLiveData<List<Mote>> _motes = new MutableLiveData<>();

    /**
     * TODO doc
     */
    public LiveData<List<Mote>> getMotes() {
        return _motes;
    }

    /**
     * TODO doc
     */
    public void setMotes(List<Mote> newMotes) {
        //postValue can be used in a background task and will trigger all observers
        _motes.postValue(newMotes);
    }

}
