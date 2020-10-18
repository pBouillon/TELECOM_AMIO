package eu.telecomnancy.amio.iotlab.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Custom collection of mote POJOs
 * @see Mote
 */
public class MoteCollection {

    /**
     * Collection of all motes, guaranteed not to be null when the object is created
     */
    public List<Mote> data = new LinkedList<>();

}
