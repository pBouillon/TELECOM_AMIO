package eu.telecomnancy.amio.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import eu.telecomnancy.amio.persistence.daos.MoteDao;
import eu.telecomnancy.amio.persistence.daos.RecordDao;
import eu.telecomnancy.amio.persistence.entities.Mote;
import eu.telecomnancy.amio.persistence.entities.Record;

/**
 * Custom database storing all data retrieved by the application from the IotLab platform
 */
@Database(entities = {
        Mote.class,
        Record.class
}, exportSchema = false, version = 1)
public abstract class IotLabDatabase extends RoomDatabase {

    /**
     * Database name, used for the SQLite file
     */
    public static final String NAME = "iotlab-database";

    /**
     * Data access object to retrieve the motes and their values
     *
     * @return The data access object
     * @see Mote
     * @see eu.telecomnancy.amio.persistence.entities.MoteWithRecords
     */
    public abstract MoteDao moteDao();

    /**
     * Data access object to retrieve the mote records and their values
     *
     * @return The data access object
     * @see Mote
     * @see eu.telecomnancy.amio.persistence.entities.MoteWithRecords
     */
    public abstract RecordDao recordDao();

}
