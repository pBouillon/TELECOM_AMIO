package eu.telecomnancy.amio.ui.main.mote.information;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import eu.telecomnancy.amio.MainActivity;
import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.iotlab.models.Mote;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoteInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoteInformationFragment extends Fragment {

    // the fragment initialization parameter key
    private static final String ARG_PARAM1 = "mote";

    // TODO: Rename and change types of parameters
    private Mote mMote;

    public MoteInformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mote the mote to display information
     * @return A new instance of fragment MoteInformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoteInformationFragment newInstance(Mote mote) {
        MoteInformationFragment fragment = new MoteInformationFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, mote);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMote = (Mote) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return inflater.inflate(R.layout.fragment_mote_information, container, false);
    }

    @Override
    public void onStop() {
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        super.onStop();
    }
}