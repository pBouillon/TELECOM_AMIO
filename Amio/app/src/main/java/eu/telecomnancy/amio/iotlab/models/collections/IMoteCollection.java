package eu.telecomnancy.amio.iotlab.models.collections;

import androidx.annotation.Nullable;

import java.util.List;

import eu.telecomnancy.amio.iotlab.dto.MoteDto;
import eu.telecomnancy.amio.iotlab.models.Mote;

/**
 * Defines a Mote collection
 *
 * @see Mote
 */
public interface IMoteCollection {

    /**
     * Given a DTO, add it if it is not stored, and merge its value with its associated Mote if
     * one mote already exists with the same name
     *
     * @param dto DTO to be merged
     */
    void addAndMergeFromDto(MoteDto dto);

    /**
     * Given its name, retrieve the associated Mote
     *
     * @param name Name of the mote to be retrieved
     * @return The associated mote or null if it is not found
     */
    @Nullable
    Mote find(String name);

    /**
     * Whether or not the collection is holding any Mote
     *
     * @return true if the collection does not contains any Mote; false otherwise
     */
    boolean isEmpty();

    /**
     * Get all stored mote as a list
     *
     * @return A list of all stored motes
     */
    List<Mote> toList();

}
