package eu.telecomnancy.amio.ui.main.mote;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.iotlab.models.Mote;

/**
 * Represent all view fields on each items in the mote recycler view
 */
public class MoteViewHolder extends RecyclerView.ViewHolder {

    /**
     * Item mapped on the view holder
     */
    public Mote item;

    /**
     * Light bulb's icon
     */
    public final ImageView lightBulb;

    /**
     * Number of lumen
     */
    public final TextView lightView;

    /**
     * Name of the mote
     */
    public final TextView nameView;

    /**
     * Temperature measured
     */
    public final TextView temperatureView;

    /**
     * View where this object is mapped
     */
    public final View view;

    /**
     * Default constructor for a MoteViewHolder
     */
    public MoteViewHolder(View view) {
        super(view);
        this.view = view;
        lightBulb = view.findViewById(R.id.light_bulb_icon);
        lightView = view.findViewById(R.id.light);
        nameView = view.findViewById(R.id.moteId);
        temperatureView = view.findViewById(R.id.humidity);
    }

}

