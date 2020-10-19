package eu.telecomnancy.amio.iotlab.entities;

import java.util.Date;

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

    public float getBrightness() {
        return brightness;
    }

    public float getHumidity() {
        return humidity;
    }

    public String getName() {
        return name;
    }

    public float getTemperature() {
        return temperature;
    }

    public long getTimestamp() {
        return timestamp;
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

        // FIXME: messy switch, use reflection to keep it DRY ?
        switch (moteDto.label) {
            // TODO: use constants
            case "light1":
                brightness = measure;
                break;
            case "humidity":
                humidity = measure;
                break;
            case "temperature":
                temperature = measure;
                break;
            default:
                throw new IllegalArgumentException("Unknown property '" + moteDto.label +"'");
        }

        // Update the timestamp if needed
        if (timestamp < moteDto.timestamp) {
            timestamp = moteDto.timestamp;
        }
    }

}
