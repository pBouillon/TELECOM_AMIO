package eu.telecomnancy.amio.persistence.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

/**
 * Representation of a record with its associated mote
 */
public class RecordAndMote {

    /**
     * Mote that retrieved this record
     */
    @Relation(
            parentColumn = "mote_source_id",
            entityColumn = "mote_id"
    )
    public Mote mote;

    /**
     * Record of the mote
     */
    @Embedded
    public Record record;

    /**
     * Convert this object to its logical representation
     *
     * @return The mote's IoTLab model
     */
    public eu.telecomnancy.amio.iotlab.models.Mote toMote() {
        return new eu.telecomnancy.amio.iotlab.models.Mote(
                record.remainingBattery,
                record.brightness,
                record.humidity,
                mote.name,
                record.temperature,
                record.retrievedAt);
    }

}
