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
    private float _brightness;

    /**
     * Humidity value retrieved by the mote
     */
    private float _humidity;

    /**
     * Mote's name
     */
    private final String _name;

    /**
     * Temperature value retrieved by the mote
     */
    private float _temperature;

    /**
     * Timestamp of the last update of this mote
     */
    private long _timestamp;

    /**
     * Default constructor
     * @param name Mote's name
     */
    public Mote(String name) {
        _name = name;
    }

    /**
     * Getter for the brightness
     * @return The brightness measured by the mote
     */
    public float getBrightness() {
        return _brightness;
    }

    /**
     * Getter for the humidity
     * @return The humidity measured by the mote
     */
    public float getHumidity() {
        return _humidity;
    }

    /**
     * Getter for the name
     * @return The mote's name
     */
    public String getName() {
        return _name;
    }

    /**
     * Getter for the temperature
     * @return The temperature measured by the mote
     */
    public float getTemperature() {
        return _temperature;
    }

    /**
     * Get the date on were the data where retrieved for the last time
     * @return A Date object from the `timestamp` property
     */
    public Date lastUpdatedOn() {
        return new Date(_timestamp);
    }

    /**
     * Merge a mote DTO's data into the mote
     * @param moteDto The DTO to be merged
     */
    public void merge(MoteDto moteDto) {
        float measure = moteDto.value;

        switch (moteDto.label) {
            case Constants.Labels.BRIGHTNESS:
                _brightness = measure;
                break;
            case Constants.Labels.HUMIDITY:
                _humidity = measure;
                break;
            case Constants.Labels.TEMPERATURE:
                _temperature = measure;
                break;
            default:
                throw new IllegalArgumentException("Unknown property '" + moteDto.label +"'");
        }

        // Update the timestamp to the most recent measure
        if (_timestamp < moteDto.timestamp) {
            _timestamp = moteDto.timestamp;
        }
    }

    @NotNull
    @Override
    public String toString() {
        return "Mote { " +
                "brightness=" + _brightness +
                ", humidity=" + _humidity +
                ", name='" + _name + '\'' +
                ", temperature=" + _temperature +
                ", timestamp=" + _timestamp +
                " }";
    }

}
