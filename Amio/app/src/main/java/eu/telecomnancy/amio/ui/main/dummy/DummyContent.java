package eu.telecomnancy.amio.ui.main.dummy;

import java.util.Random;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    public static DummySensor createDummyItem(int position) {
        return new DummySensor(String.valueOf(position), new Random().nextDouble()*100, new Random().nextDouble()*100, false);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummySensor {
        public static final double BRIGHTNESS_CEIL_ON_OFF = 40;

        public final String id;
        public final double brightness;
        public final double temperature;
        public boolean starred;

        public DummySensor(String id, double brightness, double temperature, boolean starred) {
            this.id = id;
            this.brightness = brightness;
            this.temperature = temperature;
            this.starred = starred;
        }
    }
}