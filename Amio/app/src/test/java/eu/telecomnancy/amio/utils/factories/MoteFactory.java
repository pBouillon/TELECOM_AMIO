package eu.telecomnancy.amio.utils.factories;

import java.util.Date;

import eu.telecomnancy.amio.iotlab.models.Mote;

/**
 * Factory to generate Mote fixtures
 */
public class MoteFactory extends FactoryBase<Mote> {

    @Override
    public Mote generate() {
        long timestamp = new Date().getTime();

        return new Mote(
                random.nextFloat(),
                random.nextFloat(),
                random.nextFloat(),
                String.valueOf(timestamp),
                random.nextFloat(),
                timestamp
        );
    }

    /**
     * Generate a new mote with a fixed brightness and random other fields
     *
     * @param brightness Brightness to be set
     * @param timestamp Timestamp at which this value should have been taken, often used for
     *                  chronological ordering
     * @return The motes newly created
     */
    public Mote generate(float brightness, long timestamp) {
        return generate(brightness, timestamp, String.valueOf(timestamp));
    }

    /**
     * Generate a new mote with a fixed brightness and random other fields
     *
     * @param brightness Brightness to be set
     * @param timestamp Timestamp at which this value should have been taken, often used for
     *                  chronological ordering
     * @param name Name of the mote to be generated
     * @return The motes newly created
     */
    public Mote generate(float brightness, long timestamp, String name) {
        return new Mote(
                random.nextFloat(),
                brightness,
                random.nextFloat(),
                name,
                random.nextFloat(),
                timestamp
        );
    }

}
