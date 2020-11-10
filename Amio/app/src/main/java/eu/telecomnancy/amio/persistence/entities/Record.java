package eu.telecomnancy.amio.persistence.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Representation of a record measured by a mote
 *
 * For its logical representation
 * @see eu.telecomnancy.amio.iotlab.models.Mote
 */
@Entity
public class Record {

    /**
     * The record id
     * This field is used by the database for internal storage
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "record_id")
    public long recordId;

    /**
     * The id of the mote that retrieved this record
     * Used as a foreign key in the MoteWithRecords relation
     *
     * @see MoteWithRecords
     */
    @ColumnInfo(name = "mote_source_id")
    public long moteSourceId;

}
