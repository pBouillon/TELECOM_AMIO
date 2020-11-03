package eu.telecomnancy.amio.notification;

/**
 * Notification constants
 */
public final class Constants {

    /**
     * Define all time spans for specific period of times
     */
    public static final class TimeSpans {

        /**
         * Define the time spans for the evening
         */
        public static final class Evening {

            /**
             * Beginning of the evening in hours
             */
            public static final int BEGINNING = 19;

            /**
             * End of the evening in hours
             */
            public static final int END = 23;

        }

        /**
         * Define the time spans for the night
         */
        public static final class Night {

            /**
             * Beginning of the night in hours
             */
            public static final int BEGINNING = 23;

            /**
             * End of the night in hours
             */
            public static final int END = 6;

        }

    }

}
