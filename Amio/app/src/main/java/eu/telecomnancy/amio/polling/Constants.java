package eu.telecomnancy.amio.polling;

/**
 * Polling constants
 */
public final class Constants {

    /**
     * Broadcast tags and names
     */
    public static final class Broadcast {

        /**
         * Identifier for the bundle that contains the new mote list in broadcast messages
         */
        public static final String BUNDLE_IDENTIFIER = "MotesBundle";

        /**
         * Identifier for the mote list inside the bundle
         */
        public static final String IDENTIFIER = "Motes";

        /**
         * Identifier of the broadcast message
         */
        public static final String UPDATED_DATA = "MoteDataUpdate";

    }

    /**
     * Polling task and parameters
     */
    public static final class Polling {

        /**
         * Elapsed milliseconds between each the polling event
         */
        public final static long POLLING_DELAY = 15_000;

    }

}
