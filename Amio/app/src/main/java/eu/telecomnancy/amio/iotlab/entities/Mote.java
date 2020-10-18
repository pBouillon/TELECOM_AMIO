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
    public float brightness;

    /**
     * Humidity value retrieved by the mote
     */
    public float humidity;

    /**
     * Mote's name
     */
    public String name;

    /**
     * Temperature value retrieved by the mote
     */
    public float temperature;

    /**
     * Timestamp of the last update of this mote
     */
    public long timestamp;

    /**
     * Default constructor
     * @param name Mote's name
     */
    public Mote(String name) {
        this.name = name;
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
        // Get the instance of the current class
        Class<?> instance = this.getClass();

        try {
            // Retrieve the appropriate field
            instance.getField(moteDto.label)
                    // Assign the mote DTO's value to this field
                    .set(instance, moteDto.value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
