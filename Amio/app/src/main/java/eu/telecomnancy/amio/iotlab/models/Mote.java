package eu.telecomnancy.amio.iotlab.models;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import eu.telecomnancy.amio.iotlab.Constants;
import eu.telecomnancy.amio.iotlab.dto.MoteDto;

/**
 * Logical representation of a mote
 *
 * @see MoteDto For the its API representation
 */
public class Mote implements Serializable {

    /**
     * Current battery level of the mote
     */
    private float _battery;

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
     * Getter for the battery
     * @return The remaining battery of the mote
     */
    public float getBattery() {
        return _battery;
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
<<<<<<< HEAD:Amio/app/src/main/java/eu/telecomnancy/amio/iotlab/models/Mote.java
     * Get the timestamp of the date on which the data was retrieved
     *
     * @return The associated timestamp
     */
    public long getTimestamp() {
        return _timestamp;
    }

    /**
     * Evaluate whether or not the room in which the sensor is is lightened
     *
     * @return true if the room is lightened; false otherwise
     * Evaluate whether or not the room in which the sensor is is lightened
     *
     * @return true if the room is lightened; false otherwise
     */
    public boolean isRoomLightened() {
        return _brightness > eu.telecomnancy.amio.notification.Constants.Thresholds.Lux.LIGHTED_ROOM;
    }

    /**
     * Merge a mote DTO's data into the mote
     * @param moteDto The DTO to be merged
     */
    public void merge(MoteDto moteDto) {
        float measure = moteDto.value;

        switch (moteDto.label) {
            case Constants.Labels.BATTERY:
                _battery = measure;
                break;
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
                throw new IllegalArgumentException("Unknown property '" + moteDto.label + "'");
        }

        // Update the timestamp to the most recent measure
        if (_timestamp < moteDto.timestamp) {
            _timestamp = moteDto.timestamp;
        }
    }

    @NotNull
    @Override
    public String toString() {
        return "Mote #" + _name +
                "\t(" + _battery + "%)\t{ " +
                "brightness: " + _brightness + "lx " +
                "\thumidity: " + _humidity + "% " +
                "\ttemperature: " + _temperature + "°C " +
                "\ttimestamp: " + _timestamp +
                " }";
    }

}
