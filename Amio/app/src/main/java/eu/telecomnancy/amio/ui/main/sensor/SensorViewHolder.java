package eu.telecomnancy.amio.ui.main.sensor;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.iotlab.entities.Mote;

/**
 * TODO doc
 */
public class SensorViewHolder extends RecyclerView.ViewHolder {

    /**
     * TODO doc
     */
    public Mote item;

    /**
     * TODO doc
     */
    public final ImageView lightBulb;

    /**
     * TODO doc
     */
    public final TextView lightView;

    /**
     * TODO doc
     */
    public final TextView nameView;

    /**
     * TODO doc
     */
    public final TextView temperatureView;

    /**
     * TODO doc
     */
    public final View view;

    /**
     * TODO doc
     */
    public SensorViewHolder(View view) {
        super(view);
        this.view = view;
        lightBulb = view.findViewById(R.id.light_bulb_icon);
        lightView = view.findViewById(R.id.light);
        nameView = view.findViewById(R.id.sensorId);
        temperatureView = view.findViewById(R.id.humidity);
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString() + " '" + temperatureView.getText() + "'";
    }

}

