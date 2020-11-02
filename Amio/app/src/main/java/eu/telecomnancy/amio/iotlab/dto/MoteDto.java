package eu.telecomnancy.amio.iotlab.dto;

/**
 * Mote DTO as described by the IotLab's API payload
 */
public class MoteDto {

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

}
