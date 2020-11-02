package eu.telecomnancy.amio.ui.main;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.ui.main.dummy.DummyContent.DummySensor;

import java.text.DecimalFormat;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummySensor}.
 */
public class SensorRecyclerViewAdapter extends RecyclerView.Adapter<SensorRecyclerViewAdapter.ViewHolder> {

    private List<DummySensor> mValues;
    //Needed to get the parametrized ressource string
    private final Context context;

    public SensorRecyclerViewAdapter(List<DummySensor> items, Context context) {
        mValues = items;
        this.context = context;
    }

    public void setValues(List<DummySensor> sensors){
        mValues = sensors;
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
        holder.mItem = mValues.get(position);
        //Bind the texts to the view
        holder.mIdView.setText(context.getString(
                R.string.sensor_id_text_holder,
                mValues.get(position).id));
        holder.mLightView.setText(context.getString(
                R.string.light_text_holder,
                df2.format(mValues.get(position).brightness)));
        if(mValues.get(position).brightness < DummySensor.BRIGHTNESS_CEIL_ON_OFF){
            holder.mLightBulb.setImageResource(R.drawable.ic_light_bulb_off);
        }else{
            holder.mLightBulb.setImageResource(R.drawable.ic_light_bulb_on);
        }

        holder.mHumidityView.setText(context.getString(
                R.string.humidity_text_holder,
                df2.format(mValues.get(position).temperature)));
        holder.mStarView.setChecked(mValues.get(position).starred);

        //Set the listener on the star checkbox that update the model
        holder.mStarView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mValues.get(position).starred = false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mLightView;
        public final TextView mHumidityView;
        public final TextView mIdView;
        public final CheckBox mStarView;
        public final ImageView mLightBulb;
        public DummySensor mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.sensorId);
            mLightView = view.findViewById(R.id.light);
            mHumidityView = view.findViewById(R.id.humidity);
            mStarView = view.findViewById(R.id.favorite_checkbox);
            mLightBulb = view.findViewById(R.id.light_bulb_icon);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mHumidityView.getText() + "'";
        }
    }
}