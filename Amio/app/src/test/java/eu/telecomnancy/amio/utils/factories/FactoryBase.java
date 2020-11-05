package eu.telecomnancy.amio.utils.factories;

import java.util.Date;
import java.util.Random;

/**
 * Define a generic factory for an object
 * @param <T> Target object to be generated
 */
abstract class FactoryBase<T> {

    /**
     * Random object to be used to generate various temperature
     */
    protected final static Random random = new Random(
            new Date().getTime());

    /**
     * Generate a dummy object of the provided type
     * @return The created object
     */
    public abstract T generate();

}
