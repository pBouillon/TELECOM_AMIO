package eu.telecomnancy.amio.ui.main;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import eu.telecomnancy.amio.iotlab.entities.Mote;

// Using a ViewModel avoid the service to be stopped and restarted wen the device orientation change (or any other thing that refrech the UI)
public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<Mote>> motes = new MutableLiveData<>();

    public LiveData<List<Mote>> getMotes(){
        return motes;
    }

    public void setMotes(List<Mote> newMotes) {
        //postValue can be used in a background task and will trigger all observers
        motes.postValue(newMotes);
    }
}