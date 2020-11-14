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
         * Mail content, in HTML
         */
        public static final String CONTENT = ""
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
         * Default mail receiver
         */
        public static final String RECEIVER = "pierre.bouillon@telecomnancy.net";

        /**
         * Default mail sender
         */
        public static final String SENDER = "iotlab-notifier@telecomnancy.net";

        /**
         * Mail subject
         */
        public static final String SUBJECT = "[IoTLab activity report] Abnormal activity detected";

        /**
         * SMTP server constants
         */
        public static final class Smtp {

            /**
             * SMTP configuration
             */
            public static final class Config {

                /**
                 * SMTP port
                 */
                public static final long PORT = 587;

                /**
                 * SMTP address
                 */
                public static final String HOST = "smtp.gmail.com";

            }

            /**
             * SMTP credentials
             */
            public static final class Credentials {

                /**
                 * Username used to access the SMTP service
                 */
                public static final String LOGIN = "";

                /**
                 * Password to access the SMTP service
                 */
                public static final String PASSWORD = "";

            }

        }

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
