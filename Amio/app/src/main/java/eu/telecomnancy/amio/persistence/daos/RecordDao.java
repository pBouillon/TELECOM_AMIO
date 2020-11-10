package eu.telecomnancy.amio.persistence.daos;

import androidx.room.Dao;
import androidx.room.Insert;

import eu.telecomnancy.amio.persistence.entities.Record;

/**
 * Record data access object, exposing methods to interact with the entity in the database
 *
 * @see Record
 */
@Dao
public interface RecordDao {

    /**
     * Insert a record in the database
     *
     * @param record The record to be inserted
     */
    @Insert
    long insert(Record record);

}
