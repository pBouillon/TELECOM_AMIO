package eu.telecomnancy.amio.persistence.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import eu.telecomnancy.amio.persistence.entities.Mote;
import eu.telecomnancy.amio.persistence.entities.MoteWithRecords;

import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * Mote data access object, exposing methods to interact with the entity in the database
 *
 * @see Mote
 * @see MoteWithRecords
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
     * Get a mote by its name
     *
     * @return The mote which name is the same as the provided one
     */
    @Query("SELECT * FROM mote WHERE name = :moteName")
    Mote getByName(String moteName);

    /**
     * Get all records of a mote
     *
     * @param moteName Name of the mote we are looking at
     * @return An entity holding the mote and all of its records
     */
    @Transaction
    @Query("SELECT * from mote WHERE name = :moteName")
    List<MoteWithRecords> getAllRecordsForMoteByName(String moteName);

    /**
     * Insert a mote in the database
     *
     * @param mote The mote to be inserted
     */
    @Insert(onConflict = REPLACE)
    long insert(Mote mote);

}
