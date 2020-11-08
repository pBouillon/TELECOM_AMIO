package eu.telecomnancy.amio.persistence.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import eu.telecomnancy.amio.persistence.models.Mote;

/**
 * Mote data access object, exposing methods to interact with the entity in the database
 *
 * @see Mote
 */
@Dao
public interface MoteDao {

    /**
     * Test whether or not a mote with the same name is registered in database
     * The test will be on the mote name and not on its preferred name
     *
     * @param moteName Name of the mote to be tested
     * @return true if any mote is stored with the same name; false otherwise
     */
    @Query("SELECT EXISTS(SELECT * FROM mote WHERE name = :moteName)")
    boolean exists(String moteName);

    /**
     * Get all tracked motes
     *
     * @return A list of all the motes tracked, ordered by their preferred name
     */
    @Query("SELECT * FROM mote ORDER BY preferred_name, name")
    List<Mote> getAll();

    /**
     * Insert a mote in the database
     *
     * @param mote The mote to be inserted
     */
    @Insert
    long insert(Mote mote);

}
