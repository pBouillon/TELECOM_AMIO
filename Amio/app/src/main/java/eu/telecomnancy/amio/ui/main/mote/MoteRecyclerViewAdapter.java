package eu.telecomnancy.amio.ui.main.mote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.iotlab.models.Mote;
import eu.telecomnancy.amio.ui.commons.FormatProvider;
import eu.telecomnancy.amio.ui.main.mote.information.MoteInformationFragment;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Mote}.
 */
public class MoteRecyclerViewAdapter
        extends RecyclerView.Adapter<MoteViewHolder> {

    /**
     * Context to access the parametrized string resource
     */
    private final Fragment _context;

    /**
     * List of the motes that are shown
     */
    private final MoteSortedList _motes;

    /**
     * Default constructor for the MoteRecyclerViewAdapter
     */
    public MoteRecyclerViewAdapter(List<Mote> items, Fragment context) {
        _motes = new MoteSortedList(this);
        _motes.addAll(items);
        _context = context;
    }

    @Override
    public int getItemCount() {
        return _motes.size();
    }

    @Override
    public void onBindViewHolder(final MoteViewHolder holder, final int position) {
        // Retrieve the mote to be updated
        Mote mote = _motes.get(position);

        // Prepare displayed texts
        String brightnessText = FormatProvider.standardDecimalFormat
                .format(mote.getBrightness());

        String temperatureText = FormatProvider.standardDecimalFormat
                .format(mote.getTemperature());

        // Bind values of the item at the given position to the holder assigned to it
        holder.item = mote;

        // Bind the texts to the view
        if (mote.getPreferredName().isEmpty() || mote.getName().equals(mote.getPreferredName())) {
            holder.nameView.setText(_context.getString(R.string.sensor_id_text_holder, mote.getName()));
            holder.idView.setText(null);
        } else {
            holder.nameView.setText(mote.getPreferredName());
            holder.idView.setText(mote.getName());
        }


        holder.lightView.setText(
                _context.getString(R.string.light_text_holder, brightnessText));

        holder.temperatureView.setText(_context.getString(
                R.string.temperature_text_holder, temperatureText));

        // Assign the relevant image
        holder.lightBulb.setImageResource(
                mote.isRoomLightened()
                        ? R.drawable.light_on
                        : R.drawable.light_off
        );

        holder.view.setOnClickListener(view -> {
            _context.getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, MoteInformationFragment.newInstance(mote))
                    .addToBackStack(null)
                    .commit();
        });
    }

    @NonNull
    @Override
    public MoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mote_view_holder, parent, false);

        return new MoteViewHolder(view);
    }

    /**
     * Set the motes and update the view
     */
    public void setMotes(List<Mote> motes) {
        // The _motes list is charged to handle duplicates
        _motes.addAll(motes);
    }

}
