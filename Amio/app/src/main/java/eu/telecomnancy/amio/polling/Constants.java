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
         * TODO doc
         */
        public static final String BUNDLE_IDENTIFIER = "MotesBundle";

        /**
         * TODO doc
         */
        public static final String IDENTIFIER = "Motes";

        /**
         * TODO doc
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
        public final static long POLLING_DELAY = 10_000;

    }

}
