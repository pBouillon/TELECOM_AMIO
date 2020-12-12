package eu.telecomnancy.amio.notification;

/**
 * Notification constants
 */
public final class Constants {

    /**
     * Mail and SMTP server's constants
     */
    public static final class Mail {

        /**
         * Subject of the mail to be sent by the user
         */
        public static final String SUBJECT = "[IoTLab activity report] Abnormal activity detected";

        /**
         * Mail contents according to the context
         */
        public static class Content {

            /**
             * Mail content, in HTML, automatically sent when the app is detecting an abnormal
             * activities in the lights' values
             */
            public static final String AUTOMATED = ""
                    + "<html>"
                    + "     <body>"
                    + "          <h1 style=\"color: #DF4554\">"
                    + "               A sensor is detecting a light on"
                    + "          </h1>"
                    + ""
                    + "          "
                    + "          <div>"
                    + "               <p>"
                    + "                    A sensor is currently detecting a light on, you might "
                    + "                    want to verify by yourself if everything is fine."
                    + "               </p>"
                    + "<br/>"
                    + "<hr/>"
                    + "               <p style=\"color: #979EA3; font-size: small\">"
                    + "                    This email has been sent to you according to your"
                    + "                    notification and alerting preferences."
                    + "                    <br/>"
                    +                      "You can change your them in your IoTLab app."
                    + "               </p>"
                    + "          </div>"
                    + "     </body>"
                    + "</html>";

            /**
             * Mail content, in plain text, which is used as a template for when the user wants
             * to send an email manually from the app's menu
             *
             * This template introduce the context and let the user free to chose how he will detail
             * and justify the mail
             *
             * This is not meant to be sent automatically
             */
            public static final String INTENDED = "" +
                    "Hi,\n" +
                    "\n" +
                    "The IoTLab sensors application is reporting some values that you might want " +
                    "to review by yourself.\n" +
                    "\n" +
                    "Indeed, I noticed that";

        }

    }

    /**
     * Notification constants
     */
    public static final class Notification {

        /**
         * Name of the notification channel used by the application
         */
        public static final String CHANNEL_ID = "eu.telecomnancy.amio.notification";

        /**
         * Vibration pattern when sending a notification
         * Each sequence is in milliseconds
         */
        public static final long[] VIBRATION_PATTERN = new long[] { 500, 500, 1_000};

    }

    /**
     * Define fixed thresholds for specific domains
     */
    public static final class Thresholds {

        /**
         * Constants for lux thresholds
         */
        public static final class Lux {

            /**
             * Threshold value at which we can consider the room as lit
             */
            public static final int LIGHTED_ROOM = 200;

        }

    }

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
