package eu.telecomnancy.amio.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import eu.telecomnancy.amio.persistence.daos.MoteDao;
import eu.telecomnancy.amio.persistence.models.Mote;

@Database(entities = {
        Mote.class
}, version = 1)
public abstract class IotLabDatabase extends RoomDatabase {

    public static final String NAME = "iotlab-database";

    public abstract MoteDao moteDao();

}
