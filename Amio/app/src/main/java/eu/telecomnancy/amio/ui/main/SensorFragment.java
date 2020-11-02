package eu.telecomnancy.amio.ui.main;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.ui.main.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 */
public class SensorFragment extends Fragment{

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 3;

    private MainViewModel mViewModel;
    private SensorRecyclerViewAdapter sensorRecyclerViewAdapter;
    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SensorFragment() {
    }

    public static SensorFragment newInstance() {
        SensorFragment fragment = new SensorFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, fragment.mColumnCount);
        fragment.setArguments(args);
        return fragment;
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
            recyclerView = (RecyclerView) view;
            updateRecyclerLayer();
            sensorRecyclerViewAdapter = new SensorRecyclerViewAdapter(new ArrayList<DummyContent.DummySensor>(), getContext());
            recyclerView.setAdapter(sensorRecyclerViewAdapter);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mViewModel.getSensors().observe(getViewLifecycleOwner(), new Observer<List<DummyContent.DummySensor>>() {
            @Override
            public void onChanged(List<DummyContent.DummySensor> dummySensors) {
                sensorRecyclerViewAdapter.setValues(dummySensors);
            }
        });
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        updateRecyclerLayer();
        super.onConfigurationChanged(newConfig);
    }

    private void updateRecyclerLayer() {
        Context context = recyclerView.getContext();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
    }
}