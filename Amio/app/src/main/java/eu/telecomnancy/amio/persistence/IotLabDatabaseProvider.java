package eu.telecomnancy.amio.persistence;

import android.content.Context;

import androidx.room.Room;

import org.jetbrains.annotations.Nullable;

public final class IotLabDatabaseProvider {

    private static IotLabDatabase _database = null;

    @Nullable
    public IotLabDatabase getInstance() {
        return _database;
    }

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
