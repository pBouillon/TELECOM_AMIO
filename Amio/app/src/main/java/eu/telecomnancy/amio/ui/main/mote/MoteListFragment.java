package eu.telecomnancy.amio.ui.main.mote;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.iotlab.models.Mote;
import eu.telecomnancy.amio.ui.main.Constants;
import eu.telecomnancy.amio.ui.main.MainViewModel;

/**
 * A fragment representing a list of motes
 */
public class MoteListFragment extends Fragment {

    /**
     * Date adapter for the mote list
     */
    private MoteRecyclerViewAdapter _moteRecyclerViewAdapter;

    /**
     * Recycler view used for the mote list re-rendering when the orientation changes
     */
    private RecyclerView _recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MoteListFragment() { }

    /**
     * Generate the URI of the content of the mail to be passed on the appropriate application
     *
     * @return the generated content of the mail, as an URI
     */
    private Uri generateMailContentUri() {
        // Retrieve the current context
        Context context = getContext();

        // Retrieve the default value to be used as the recipient
        String defaultMailIntentAddress = context.getResources()
                .getString(R.string.default_mail_intent_address);

        // Retrieve the preference's recipient
        String mailIntentAddressKey = context.getResources()
                .getString(R.string.mail_intent_address_key);

        String mailIntentAddress = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(mailIntentAddressKey, defaultMailIntentAddress);

        // Create the mail intended to the appropriate recipient
        String content = "mailto:" +
                Uri.encode(mailIntentAddress) +
                "?subject=" +
                Uri.encode(eu.telecomnancy.amio.notification.Constants.Mail.SUBJECT) +
                "&body=" +
                Uri.encode(eu.telecomnancy.amio.notification.Constants.Mail.Content.INTENDED);

        return Uri.parse(content);
    }

    /**
     * Open the mailing app to notify an abnormal activity
     */
    private void fireMailIntent() {
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setData(generateMailContentUri());

        startActivity(mailIntent);
    }

    /**
     * Give the right way to construct this fragment
     *
     * @return The instantiated fragment
     */
    public static MoteListFragment newInstance() {
        Bundle args = new Bundle();

        args.putInt(
                Constants.MoteList.ARG_COLUMN_COUNT,
                Constants.MoteList.COLUMN_COUNT);

        MoteListFragment fragment = new MoteListFragment();
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
                        motes -> _moteRecyclerViewAdapter.setMotes(motes));
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
        View view = inflater.inflate(R.layout.fragment_mote_list, container, false);
        _recyclerView = view.findViewById(R.id.mote_recycler_list);

        // Set the adapter
        updateRecyclerLayer();
        _moteRecyclerViewAdapter = new MoteRecyclerViewAdapter(new ArrayList<>(), getContext());
        _recyclerView.setAdapter(_moteRecyclerViewAdapter);

        FloatingActionButton mailFab = view.findViewById(R.id.mail_fab);
        mailFab.setOnClickListener(v -> fireMailIntent());

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
                : new GridLayoutManager(context, Constants.MoteList.COLUMN_COUNT));
    }

}
