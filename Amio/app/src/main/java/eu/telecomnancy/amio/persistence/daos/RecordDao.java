package eu.telecomnancy.amio.persistence.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import eu.telecomnancy.amio.persistence.entities.Record;
import eu.telecomnancy.amio.persistence.entities.RecordAndMote;

/**
 * Record data access object, exposing methods to interact with the entity in the database
 *
 * @see Record
 */
@Dao
public interface RecordDao {

    /**
     * Given its id, get the latest records of the mote
     *
     * @param moteId Id of the queried mote
     * @param historySize number of previous records to fetch
     * @return A list containing at most the two last values measured by this mote
     */
    @Transaction
    @Query("SELECT * FROM record " +
            "WHERE mote_source_id = :moteId " +
            "ORDER BY retrieved_at " +
            "DESC LIMIT :historySize")
    List<RecordAndMote> getLatestRecordAndMotePairById(long moteId, int historySize);

    /**
     * Insert a record in the database
     *
     * @param record The record to be inserted
     */
    @Insert
    long insert(Record record);

}
