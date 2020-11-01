package eu.telecomnancy.amio.iotlab;

/**
 * IoTLab constants
 */
public final class Constants {

    /**
     * Labels as defined and exposed by the IoTLab API
     */
    public static final class Labels {

        /**
         * IoTLab label for brightness
         */
        public final static String BRIGHTNESS = "light1";

        /**
         * IoTLab label for humidity
         */
        public final static String HUMIDITY = "humidity";

        /**
         * IoTLab label for temperature
         */
        public final static String TEMPERATURE = "temperature";

    }

    public static final class Polling {

        /**
         * Elapsed milliseconds between each the polling event
         */
        public final static long POLLING_DELAY = 3_000;

    }

    /**
     * IoTLab custom URLs
     */
    public static final class Urls {

        /**
         * Deployed web service, live at: http://iotlab.telecomnancy.eu:8080/iotlab/
         */
        public final static String Api = "http://iotlab.telecomnancy.eu:8080/iotlab/rest/data/1";

    }

}
