package eu.telecomnancy.amio.iotlab.dto;

import java.util.LinkedList;
import java.util.List;

/**
 * Custom collection of mote DTOs
 * @see MoteDto
 */
public class MoteDtoCollection {

    /**
     * Collection of all motes, guaranteed not to be null when the object is created
     */
    public List<MoteDto> data = new LinkedList<>();

}
