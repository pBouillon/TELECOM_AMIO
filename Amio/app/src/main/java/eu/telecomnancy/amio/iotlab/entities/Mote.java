package eu.telecomnancy.amio.iotlab.entities;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

import eu.telecomnancy.amio.iotlab.Constants;
import eu.telecomnancy.amio.iotlab.dto.MoteDto;

/**
 * Logical representation of a mote
 * @see MoteDto For the its API representation
 */
public class Mote {

    /**
     * Brightness value retrieved by the mote
     */
    private float brightness;

    /**
     * Humidity value retrieved by the mote
     */
    private float humidity;

    /**
     * Mote's name
     */
    private String name;

    /**
     * Temperature value retrieved by the mote
     */
    private float temperature;

    /**
     * Timestamp of the last update of this mote
     */
    private long timestamp;

    /**
     * Default constructor
     * @param name Mote's name
     */
    public Mote(String name) {
        this.name = name;
    }

    /**
     * Getter for the brightness
     * @return The brightness measured by the mote
     */
    public float getBrightness() {
        return brightness;
    }

    /**
     * Getter for the humidity
     * @return The humidity measured by the mote
     */
    public float getHumidity() {
        return humidity;
    }

    /**
     * Getter for the name
     * @return The mote's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the temperature
     * @return The temperature measured by the mote
     */
    public float getTemperature() {
        return temperature;
    }

    /**
     * Get the date on were the data where retrieved for the last time
     * @return A Date object from the `timestamp` property
     */
    public Date lastUpdatedOn() {
        return new Date(timestamp);
    }

    /**
     * Merge a mote DTO's data into the mote
     * @param moteDto The DTO to be merged
     */
    public void merge(MoteDto moteDto) {
        float measure = moteDto.value;

        switch (moteDto.label) {
            case Constants.Labels.BRIGHTNESS:
                brightness = measure;
                break;
            case Constants.Labels.HUMIDITY:
                humidity = measure;
                break;
            case Constants.Labels.TEMPERATURE:
                temperature = measure;
                break;
            default:
                throw new IllegalArgumentException("Unknown property '" + moteDto.label +"'");
        }

        // Update the timestamp to the most recent measure
        if (timestamp < moteDto.timestamp) {
            timestamp = moteDto.timestamp;
        }
    }

    @NotNull
    @Override
    public String toString() {
        return "Mote { " +
                "brightness=" + brightness +
                ", humidity=" + humidity +
                ", name='" + name + '\'' +
                ", temperature=" + temperature +
                ", timestamp=" + timestamp +
                " }";
    }

}
