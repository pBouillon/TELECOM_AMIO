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

    /**
     * IoTLab custom URLs
     */
    public static final class Urls {

        /**
         * Deployed web service, live at: http://iotlab.telecomnancy.eu:8080/iotlab/
         */
        public final static String API = "http://iotlab.telecomnancy.eu:8080/iotlab/rest/data/1";

    }

}
