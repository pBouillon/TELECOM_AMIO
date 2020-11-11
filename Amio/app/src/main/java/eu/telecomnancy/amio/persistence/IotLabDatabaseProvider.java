package eu.telecomnancy.amio.persistence;

import android.content.Context;

import androidx.room.Room;

import org.jetbrains.annotations.Nullable;

/**
 * Database wrapper to manage a single instance of the database
 *
 * @see IotLabDatabase
 */
public final class IotLabDatabaseProvider {

    /**
     * Instance of the database, managed as a singleton
     */
    private static IotLabDatabase _database = null;

    /**
     * Retrieve the instance of the database held by the wrapper
     *
     * If the instance is not already created, this will initialize it
     *
     * @param context Android context used to establish the connection with the database on creation
     * @return The Database object to access its DAOs
     */
    public static IotLabDatabase getOrCreateInstance(Context context) {
        if (_database == null) {
            _database = Room
                    .databaseBuilder(context, IotLabDatabase.class, IotLabDatabase.NAME)
                    .enableMultiInstanceInvalidation()
                    .build();
        }

        return _database;
    }

}
