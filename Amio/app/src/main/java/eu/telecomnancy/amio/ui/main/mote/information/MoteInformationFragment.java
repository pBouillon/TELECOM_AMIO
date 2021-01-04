package eu.telecomnancy.amio.ui.main.mote.information;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import eu.telecomnancy.amio.MainActivity;
import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.iotlab.models.Mote;
import eu.telecomnancy.amio.persistence.IotLabDatabaseProvider;
import eu.telecomnancy.amio.persistence.daos.MoteDao;
import eu.telecomnancy.amio.persistence.utils.IoTLabPersistenceUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoteInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoteInformationFragment extends Fragment {

    /**
     * The fragment initialization parameter key
     */
    private static final String ARG_PARAM1 = "mote";

    /**
     * Mote displayed in this frame
     */
    private Mote _Mote;

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
            _Mote = (Mote) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity())
                .getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mote_information, container, false);
        TextView moteIdTv = view.findViewById(R.id.moteInformationID);
        moteIdTv.setText(_Mote.getName());

        EditText motePreferredName = view.findViewById(R.id.motePreferredName);
        motePreferredName.setText(_Mote.getPreferredName());

        motePreferredName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MoteDao moteDao = IotLabDatabaseProvider.getOrCreateInstance(getContext()).moteDao();
                IoTLabPersistenceUtils.changeMotePreferredName(_Mote, moteDao, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) { }

        });

        return view;
    }

    @Override
    public void onStop() {
        ((MainActivity) getActivity())
                .getSupportActionBar()
                .setDisplayHomeAsUpEnabled(false);

        super.onStop();
    }

}
