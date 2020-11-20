package eu.telecomnancy.amio.ui.main.mote;

import androidx.recyclerview.widget.SortedList;

import eu.telecomnancy.amio.iotlab.models.Mote;

/**
 * Represent a specialized Sorted list.
 * This class can compare a collection to the existing one to update only changed items
 *
 * @see androidx.recyclerview.widget.SortedList
 */
public class MoteSortedList extends SortedList<Mote> {

    /**
     * constructor for MoteSortedList
     *
     * @param moteRecyclerViewAdapter the recycler view where this list is shown. Needed to notify on data change.
     */
    MoteSortedList(MoteRecyclerViewAdapter moteRecyclerViewAdapter) {
        super(Mote.class, new Callback<Mote>() {
            @Override
            public int compare(Mote o1, Mote o2) {
                return o1.getName().compareTo(o2.getName());
            }

            @Override
            public void onChanged(int position, int count) {
                moteRecyclerViewAdapter.notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Mote oldItem, Mote newItem) {
                return oldItem.getBattery() == newItem.getBattery() &&
                        oldItem.isRoomLightened() == newItem.isRoomLightened() &&
                        oldItem.getHumidity() == newItem.getHumidity() &&
                        oldItem.getTemperature() == newItem.getTemperature();
            }

            @Override
            public boolean areItemsTheSame(Mote item1, Mote item2) {
                return item1.getName().equals(item2.getName());
            }

            @Override
            public void onInserted(int position, int count) {
                moteRecyclerViewAdapter.notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                moteRecyclerViewAdapter.notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                moteRecyclerViewAdapter.notifyItemMoved(fromPosition, toPosition);
            }
        });
    }
}
