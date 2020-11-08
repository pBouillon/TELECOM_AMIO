package eu.telecomnancy.amio.ui.main.sensor;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.iotlab.entities.Mote;
import eu.telecomnancy.amio.ui.main.Constants;
import eu.telecomnancy.amio.ui.main.MainViewModel;

/**
 * A fragment representing a list of items
 */
public class SensorFragment extends Fragment {

    /**
     * TODO doc
     */
    private RecyclerView _recyclerView;

    /**
     * TODO doc
     */
    private SensorRecyclerViewAdapter _sensorRecyclerViewAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SensorFragment() { }

    /**
     * TODO doc
     */
    public static SensorFragment newInstance() {
        Bundle args = new Bundle();
        args.putInt(Constants.Sensor.ARG_COLUMN_COUNT, Constants.Sensor.COLUMN_COUNT);

        SensorFragment fragment = new SensorFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainViewModel viewModel = new ViewModelProvider(requireActivity())
                .get(MainViewModel.class);

        viewModel.getMotes()
                .observe(
                        getViewLifecycleOwner(),
                        dummySensors -> _sensorRecyclerViewAdapter.setMotes(dummySensors));
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        updateRecyclerLayer();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            _recyclerView = (RecyclerView) view;
            updateRecyclerLayer();
            _sensorRecyclerViewAdapter = new SensorRecyclerViewAdapter(new ArrayList<Mote>(), getContext());
            _recyclerView.setAdapter(_sensorRecyclerViewAdapter);
        }

        return view;
    }

    /**
     * Update the recycler view to mach the current orientation
     */
    private void updateRecyclerLayer() {
        Context context = _recyclerView.getContext();

        boolean isOrientationPortrait = getResources()
                .getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT;

        _recyclerView.setLayoutManager(isOrientationPortrait
                ? new LinearLayoutManager(context)
                : new GridLayoutManager(context, Constants.Sensor.COLUMN_COUNT));
    }

}
