package eu.telecomnancy.amio.ui.main;

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

/**
 * A fragment representing a list of Items.
 */
public class SensorFragment extends Fragment {

    /**
     * TODO doc
     * TODO to const file
     */
    private static final String ARG_COLUMN_COUNT = "column-count";

    /**
     * TODO doc
     * TODO to const file
     */
    private static final int COLUMN_COUNT = 3;

    /**
     * TODO doc
     */
    private RecyclerView _recyclerView;

    /**
     * TODO
     */
    private SensorRecyclerViewAdapter _sensorRecyclerViewAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SensorFragment() { }

    /**
     * TODO
     */
    public static SensorFragment newInstance() {
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, COLUMN_COUNT);

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
                : new GridLayoutManager(context, COLUMN_COUNT));
    }

}
