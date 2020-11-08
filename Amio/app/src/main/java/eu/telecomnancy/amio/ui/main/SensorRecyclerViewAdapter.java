package eu.telecomnancy.amio.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.iotlab.entities.Mote;
import eu.telecomnancy.amio.notification.Constants;
import eu.telecomnancy.amio.ui.commons.FormatProvider;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Mote}.
 */
public class SensorRecyclerViewAdapter
        extends RecyclerView.Adapter<SensorRecyclerViewAdapter.ViewHolder> {

    /**
     * Context to access the parametrized string resource
     */
    private final Context _context;

    private List<Mote> _motes;

    public SensorRecyclerViewAdapter(List<Mote> items, Context context) {
        _motes = items;
        _context = context;
    }

    public void setMotes(List<Mote> motes) {
        _motes = motes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sensor_view_holder, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
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
        holder.mLightBulb.setImageResource(
                mote.isRoomLightened()
                        ? R.drawable.ic_light_bulb_on
                        : R.drawable.ic_light_bulb_off
        );
    }

    @Override
    public int getItemCount() {
        return _motes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView lightView;
        public final TextView temperatureView;
        public final TextView nameView;
        public final ImageView mLightBulb;
        public Mote item;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nameView = view.findViewById(R.id.sensorId);
            lightView = view.findViewById(R.id.light);
            temperatureView = view.findViewById(R.id.humidity);
            mLightBulb = view.findViewById(R.id.light_bulb_icon);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + temperatureView.getText() + "'";
        }
    }
}
