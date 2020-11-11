package eu.telecomnancy.amio.persistence.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

/**
 * Mote representation
 * A mote is a single sensor that will retrieve data of several types
 *
 * For its logical representation
 * @see eu.telecomnancy.amio.iotlab.models.Mote
 */
@Entity
public class Mote {

    /**
     * The mote id
     * This field is used by the database for internal storage
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mote_id")
    public long moteId;

    /**
     * The mote's name as specified by the user
     */
    @ColumnInfo(name = "preferred_name", defaultValue = "")
    public String preferredName;

    /**
     * The mote's name as exposed by the API
     */
    @ColumnInfo(name = "name")
    public String name;

    /**
     * Create a new mote from its name
     * By default, the preferred name will be the same as name
     *
     * @param name Mote name
     */
    public Mote(String name) {
        this.name = name;
        preferredName = name;
    }

    @NotNull
    @Override
    public String toString() {
        return "Mote { " +
                "moteId=" + moteId +
                ", preferredName='" + preferredName + '\'' +
                ", name='" + name + '\'' +
                " }";
    }

}
