package eu.telecomnancy.amio.ui.main;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.notification.Constants;
import eu.telecomnancy.amio.iotlab.entities.Mote;

import java.text.DecimalFormat;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Mote}.
 */
public class SensorRecyclerViewAdapter extends RecyclerView.Adapter<SensorRecyclerViewAdapter.ViewHolder> {

    private List<Mote> mMotes;
    //Needed to get the parametrized ressource string
    private final Context context;

    public SensorRecyclerViewAdapter(List<Mote> items, Context context) {
        mMotes = items;
        this.context = context;
    }

    public void setMotes(List<Mote> motes){
        mMotes = motes;
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
        DecimalFormat df2 = new DecimalFormat("#.##");

        //Bind values of the item at the given position to the holder assigned to it
        holder.mItem = mMotes.get(position);
        //Bind the texts to the view
        holder.mIdView.setText(context.getString(
                R.string.sensor_id_text_holder,
                mMotes.get(position).getName()));
        holder.mLightView.setText(context.getString(
                R.string.light_text_holder,
                df2.format(mMotes.get(position).getBrightness())));

        if(mMotes.get(position).getBrightness() < Constants.Thresholds.Lux.LIGHTED_ROOM){
            holder.mLightBulb.setImageResource(R.drawable.ic_light_bulb_off);
        }else{
            holder.mLightBulb.setImageResource(R.drawable.ic_light_bulb_on);
        }

        holder.mHumidityView.setText(context.getString(
                R.string.humidity_text_holder,
                df2.format(mMotes.get(position).getTemperature())));

    }

    @Override
    public int getItemCount() {
        return mMotes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mLightView;
        public final TextView mHumidityView;
        public final TextView mIdView;
        public final ImageView mLightBulb;
        public Mote mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.sensorId);
            mLightView = view.findViewById(R.id.light);
            mHumidityView = view.findViewById(R.id.humidity);
            mLightBulb = view.findViewById(R.id.light_bulb_icon);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mHumidityView.getText() + "'";
        }
    }
}