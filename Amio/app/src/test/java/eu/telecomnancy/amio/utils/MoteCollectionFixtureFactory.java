package eu.telecomnancy.amio.utils;

import java.util.Date;
import java.util.Random;

import eu.telecomnancy.amio.iotlab.Constants;
import eu.telecomnancy.amio.iotlab.dto.MoteDto;
import eu.telecomnancy.amio.iotlab.entities.collections.IMoteCollection;
import eu.telecomnancy.amio.iotlab.entities.collections.MoteCollection;

/**
 * Factory to generate IMoteCollection fixture
 */
public final class MoteCollectionFixtureFactory {

    /**
     * Maximum number of motes to be generated to populate the IMoteCollection
     */
    private final static int MAXIMUM_NUMBER_OF_MOTES_GENERATED = 1_000;

    /**
     * Random object to be used to generate various temperature
     */
    private final static Random random = new Random(
            new Date().getTime());

    /**
     * Create an IMoteCollection with the brightness value of each of them between 0
     * and maximumLuxValue
     *
     * @param maximumLuxValue Maximum value of each mote's brightness property
     * @return The created IMoteCollection holding the generated motes
     */
    public static IMoteCollection generateMoteCollection(int maximumLuxValue) {
        return generateMoteCollection(0, maximumLuxValue);
    }

    /**
     * Create an IMoteCollection with the brightness value of each of them between minLuxValue
     * and maximumLuxValue
     *
     * @param minLuxValue Minimum value of each mote's brightness property
     * @param maximumLuxValue Maximum value of each mote's brightness property
     * @return The created IMoteCollection holding the generated motes
     */
    public static IMoteCollection generateMoteCollection(int minLuxValue, int maximumLuxValue)
            throws IllegalArgumentException{
        if (maximumLuxValue < minLuxValue) {
            throw new IllegalArgumentException(
                    "The maximum value cannot be greater than the minimum");
        }

        IMoteCollection motes = new MoteCollection();

        int numberOfMotesToBeGenerated = random.nextInt(MAXIMUM_NUMBER_OF_MOTES_GENERATED);

        MoteDto generatedMote;

        for (int i = 0; i < numberOfMotesToBeGenerated; ++i) {
            int generatedLuxValue =
                    random.nextInt((maximumLuxValue - minLuxValue) + 1) + minLuxValue;

            generatedMote = new MoteDto();
            generatedMote.mote = String.valueOf(new Date().getTime());
            generatedMote.label = Constants.Labels.BRIGHTNESS;
            generatedMote.value = generatedLuxValue;

            motes.addAndMergeFromDto(generatedMote);
        }

        return motes;
    }

}
