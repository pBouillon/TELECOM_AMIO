package eu.telecomnancy.amio.ui.main;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import eu.telecomnancy.amio.ui.main.dummy.DummyContent;

// Using a ViewModel avoid the service to be stopped and restarted wen the device orientation change (or any other thing that refrech the UI)
public class MainViewModel extends ViewModel {

    private MutableLiveData<List<DummyContent.DummySensor>> sensorList;

    public LiveData<List<DummyContent.DummySensor>> getSensors(){
        if(sensorList == null){
            sensorList = new MutableLiveData<>();
            loadSensorInformation();
        }
        return sensorList;
    }

    private void loadSensorInformation() {
        List<DummyContent.DummySensor> sensors = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            sensors.add(DummyContent.createDummyItem(i));
        }
        //postValue can be used in a background task and will trigger all observers
        sensorList.postValue(sensors);
    }
}