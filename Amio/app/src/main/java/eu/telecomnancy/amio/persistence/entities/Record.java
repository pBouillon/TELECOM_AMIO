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

    @ColumnInfo(name = "brightness")
    public double brightness;

    @ColumnInfo(name = "humidity")
    public double humidity;

    @ColumnInfo(name = "remaining_battery")
    public double remainingBattery;

    @ColumnInfo(name = "retrieved_at")
    public long retrievedAt;

    @ColumnInfo(name = "temperature")
    public double temperature;

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
    public Record(long moteSourceId, double brightness, double humidity, double remainingBattery,
                  long retrievedAt, double temperature) {
        this.moteSourceId = moteSourceId;

        this.brightness = brightness;
        this.humidity = humidity;
        this.remainingBattery = remainingBattery;
        this.retrievedAt = retrievedAt;
        this.temperature = temperature;
    }

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
