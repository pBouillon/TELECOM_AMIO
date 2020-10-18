package eu.telecomnancy.amio.iotlab.entities;

import java.util.Date;

/**
 * Mote POJO as described by the IotLab's API payload
 */
public class Mote {

    /**
     * Last updated on (timestamp in ms)
     */
    public long timestamp;

    /**
     * Label of the data retrieved
     */
    public String label;

    /**
     * Value of the data represented by the label property
     */
    public float value;

    /**
     * Mote name
     */
    public String mote;

    /**
     * Get the date on were the data where retrieved for the last time
     * @return A Date object from the `timestamp` property
     */
    public Date lastUpdatedOn() {
        return new Date(timestamp);
    }

}
