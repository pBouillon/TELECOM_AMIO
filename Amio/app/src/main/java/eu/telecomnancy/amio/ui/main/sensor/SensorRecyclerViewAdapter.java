package eu.telecomnancy.amio.ui.main.sensor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.iotlab.entities.Mote;
import eu.telecomnancy.amio.ui.commons.FormatProvider;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Mote}.
 */
public class SensorRecyclerViewAdapter
        extends RecyclerView.Adapter<SensorViewHolder> {

    /**
     * Context to access the parametrized string resource
     */
    private final Context _context;

    /**
     * TODO doc
     */
    private List<Mote> _motes;

    /**
     * TODO doc
     */
    public SensorRecyclerViewAdapter(List<Mote> items, Context context) {
        _motes = items;
        _context = context;
    }

    /**
     * TODO doc
     */
    public void setMotes(List<Mote> motes) {
        _motes = motes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SensorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sensor_view_holder, parent, false);

        return new SensorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SensorViewHolder holder, final int position) {
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
        holder.nameView.setText(
                _context.getString(R.string.sensor_id_text_holder, mote.getName()));

        holder.lightView.setText(
                _context.getString(R.string.light_text_holder, brightnessText));

        holder.temperatureView.setText(_context.getString(
                R.string.temperature_text_holder, temperatureText));

        // Assign the relevant image
        holder.lightBulb.setImageResource(
                mote.isRoomLightened()
                        ? R.drawable.ic_light_bulb_on
                        : R.drawable.ic_light_bulb_off
        );
    }

    @Override
    public int getItemCount() {
        return _motes.size();
    }

}
