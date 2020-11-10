package eu.telecomnancy.amio.persistence.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

/**
 * Representation of a mote with all the records it has retrieved
 */
public class MoteWithRecords {

    /**
     * Mote that retrieved those records
     */
    @Embedded
    public Mote mote;

    /**
     * List of all records recorded by the mote
     */
    @Relation(
            parentColumn = "mote_id",
            entityColumn = "mote_source_id"
    )
    public List<Record> records;

}
