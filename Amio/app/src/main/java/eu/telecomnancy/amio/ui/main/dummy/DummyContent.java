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
        return new DummySensor(String.valueOf(position), new Random().nextInt(2)==1, new Random().nextInt(101), false);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummySensor {
        public final String id;
        public final boolean light;
        public final int temperature;
        public boolean starred;

        public DummySensor(String id, boolean light, int temperature, boolean starred) {
            this.id = id;
            this.light = light;
            this.temperature = temperature;
            this.starred = starred;
        }
    }
}