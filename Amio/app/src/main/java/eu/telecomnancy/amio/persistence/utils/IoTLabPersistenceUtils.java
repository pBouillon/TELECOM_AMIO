package eu.telecomnancy.amio.persistence.utils;

import android.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

import eu.telecomnancy.amio.iotlab.models.Mote;
import eu.telecomnancy.amio.persistence.daos.MoteDao;
import eu.telecomnancy.amio.persistence.daos.RecordDao;
import eu.telecomnancy.amio.persistence.entities.Record;

/**
 * Utility class wrapping common operation performed on the database
 */
public final class IoTLabPersistenceUtils {

    /**
     * Get a filtered list of all motes that are not stored in the database
     *
     * @param motes The list of motes to be checked
     * @param moteDao The mote DAO
     * @return A filtered list of all motes that are not stored in the database
     */
    public static List<Mote> getNonStoredMotes(List<Mote> motes, MoteDao moteDao) {
        return motes.stream()
                // Filtering only the ones not existing in the database
                .filter(mote
                        -> !moteDao.exists(mote.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Get a filtered list of all records that are not stored in the database from the original
     * measure
     *
     * @param motes  The list of motes to be checked
     * @param moteDao The mote DAO
     * @param recordDao The record DAO
     * @return A filtered list of all records that are not stored in the database from the motes
     */
    public static List<Record> getNonStoredRecordsFromRawMotes(
            List<Mote> motes, MoteDao moteDao, RecordDao recordDao) {
        return motes.stream()
                // Creating the record from the raw measure
                .map(mote -> {
                    long moteId = moteDao.getByName(mote.getName()).moteId;
                    return Record.fromMote(moteId, mote);
                })
                // Filtering only the new records
                .filter(record
                        // Retrieve the previous record of the same mote
                        -> recordDao.getLatestRecordAndMotePairById(record.moteSourceId, 1)
                        .stream()
                        // Keeping only the previous record measure's timestamp
                        .map(recordAndMote
                                -> recordAndMote.record.retrievedAt)
                        // Checking if any of this timestamp match the new record's timestamp
                        .noneMatch(previousRecordTimestamp
                                -> record.retrievedAt == previousRecordTimestamp))
                // Return the filtered list of records
                .collect(Collectors.toList());
    }

}
