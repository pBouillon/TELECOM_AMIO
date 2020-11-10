package eu.telecomnancy.amio.iotlab.dto;

import java.util.HashMap;
import java.util.Map;

import eu.telecomnancy.amio.iotlab.entities.Mote;
import eu.telecomnancy.amio.iotlab.entities.collections.IMoteCollection;
import eu.telecomnancy.amio.iotlab.entities.collections.MoteCollection;

/**
 * Aggregator for all motes DTOs, provide an API to merge all of them into concrete entities
 */
public class MoteCollectionDtoAggregator {

    /**
     * Inner-collection to track MoteDtoCollection per label
     */
    private final Map<String, MoteDtoCollection> _motesDtoByData = new HashMap<>();

    /**
     * Merge all DTOs held by this aggregator and create fully completed Motes
     *
     * @return A collection of motes according to the data retrieved
     * @see Mote
     */
    public IMoteCollection generateMoteCollectionFromAggregated() {
        IMoteCollection moteCollection = new MoteCollection();

        // From all our data sources
        _motesDtoByData.values()
                // Merge their respected values into the mote collection
                .forEach(list -> list.data.forEach(moteCollection::addAndMergeFromDto));

        return moteCollection;
    }

    /**
     * Associate a mote collection to specific label
     *
     * @param label           Label categorizing the mote collection
     * @param associatedMotes Associated collection of motes
     */
    public void aggregateMotesFor(String label, MoteDtoCollection associatedMotes) {
        _motesDtoByData.put(label, associatedMotes);
    }

}
