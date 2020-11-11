package eu.telecomnancy.amio.entities.collections;

import org.junit.jupiter.api.Test;

import eu.telecomnancy.amio.iotlab.dto.MoteDto;
import eu.telecomnancy.amio.iotlab.models.Mote;
import eu.telecomnancy.amio.iotlab.models.collections.IMoteCollection;
import eu.telecomnancy.amio.iotlab.models.collections.MoteCollection;
import eu.telecomnancy.amio.utils.factories.MoteDtoFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit testing suite for the MoteCollection collection
 * @see IMoteCollection
 */
public class MoteCollectionTest {

    /**
     * Factory used to generate fixture data
     */
    private final MoteDtoFactory _factory = new MoteDtoFactory();

    /**
     * Ensure that when adding a new mote, it is effectively added in the collection
     */
    @Test
    public void addAndMergeFromDto_addAnNewItem() {
        // Arrange
        MoteDto moteDto = _factory.generate();
        IMoteCollection collection = new MoteCollection();

        // Act
        collection.addAndMergeFromDto(moteDto);

        // Assert
        assertEquals(1, collection.toList().size());
    }

    /**
     * Ensure that when adding a mote, there is no duplicate and the value is updated instead
     */
    @Test
    public void addAndMergeFromDto_addAnExistingItem() {
        // Arrange
        MoteDto moteDto = _factory.generate();

        IMoteCollection collection = new MoteCollection();
        collection.addAndMergeFromDto(moteDto);

        MoteDto newMote = _factory.generate();
        newMote.mote = moteDto.mote;

        // Act
        collection.addAndMergeFromDto(newMote);

        // Assert
        assertEquals(1, collection.toList().size());
    }

    /**
     * Ensure that the collection is empty when just created
     */
    @Test
    public void addAndMergeFromDto_isEmptyOnCreation() {
        // Arrange
        IMoteCollection collection = new MoteCollection();

        // Act
        boolean isEmpty = collection.isEmpty();

        // Assert
        assertTrue(isEmpty);
    }

    /**
     * Ensure that when looking for an existing mote we successfully retrieve it
     */
    @Test
    public void find_searchForAnExistingItem() {
        // Arrange
        MoteDto moteDto = _factory.generate();

        IMoteCollection collection = new MoteCollection();
        collection.addAndMergeFromDto(moteDto);

        // Act
        Mote retrievedMote = collection.find(moteDto.mote);

        // Assert
        assertNotNull(retrievedMote);
        assertEquals(retrievedMote.getName(), moteDto.mote);
    }

    /**
     * Ensure that when looking for a non-existing mote we don't raise any error
     */
    @Test
    public void find_searchForANonExistingItem() {
        // Arrange
        MoteDto moteDto = _factory.generate();

        IMoteCollection collection = new MoteCollection();
        collection.addAndMergeFromDto(moteDto);

        String nonExistingMoteName = moteDto.mote + moteDto.label;

        // Act
        Mote retrievedMote = collection.find(nonExistingMoteName);

        // Assert
        assertNull(retrievedMote);
    }

}
