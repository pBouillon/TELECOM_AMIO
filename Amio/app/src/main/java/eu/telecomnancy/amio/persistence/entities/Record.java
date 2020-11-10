package eu.telecomnancy.amio.persistence.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import eu.telecomnancy.amio.iotlab.models.Mote;

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

    /**
     * The brightness measured in lux
     */
    @ColumnInfo(name = "brightness")
    public float brightness;

    /**
     * The humidity measured in percentage
     */
    @ColumnInfo(name = "humidity")
    public float humidity;

    /**
     * The remaining battery of the mote in percentage
     */
    @ColumnInfo(name = "remaining_battery")
    public float remainingBattery;

    /**
     * The timestamp corresponding to the moment where this data was retrieved
     */
    @ColumnInfo(name = "retrieved_at")
    public long retrievedAt;

    /**
     * The temperature measured in Celsius
     */
    @ColumnInfo(name = "temperature")
    public float temperature;

    /**
     * Create a new record from the id of the mote it comes from
     *
     * @param moteSourceId Id of the mote that retrieved this record
     * @param brightness Brightness in lux
     * @param humidity Humidity in percentage
     * @param remainingBattery Battery in percentage
     * @param retrievedAt Timestamp at which the data was retrieved
     * @param temperature Temperature in Celsius
     */
    public Record(long moteSourceId, float brightness, float humidity, float remainingBattery,
                  long retrievedAt, float temperature) {
        this.moteSourceId = moteSourceId;

        this.brightness = brightness;
        this.humidity = humidity;
        this.remainingBattery = remainingBattery;
        this.retrievedAt = retrievedAt;
        this.temperature = temperature;
    }

    @NotNull
    @Override
    public String toString() {
        return "Record { " +
                "recordId=" + recordId +
                ", moteSourceId=" + moteSourceId +
                ", brightness=" + brightness +
                ", humidity=" + humidity +
                ", remainingBattery=" + remainingBattery +
                ", retrievedAt=" + retrievedAt +
                ", temperature=" + temperature +
                " }";
    }

}
