package eu.telecomnancy.amio.iotlab.models.collections;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.telecomnancy.amio.iotlab.dto.MoteDto;
import eu.telecomnancy.amio.iotlab.models.Mote;

/**
 * Concrete implementation of the IMoteCollection, based on HashMap
 */
public class MoteCollection implements IMoteCollection {

    /**
     * Inner-collection to keep track of the registered motes
     */
    private final Map<String, Mote> _moteMap = new HashMap<>();

    @Override
    public void addAndMergeFromDto(MoteDto dto) {
        String identifier = dto.mote;

        // If the value does not exist in the map, create it
        _moteMap.putIfAbsent(identifier, new Mote(identifier));

        // Retrieve the stored value
        // This is guaranteed not to be null because in the worst case, the new Mote has just been
        // added previously
        //
        // Note: Retrieving the mote after its creation in the worst case does not affect performances
        // since HashMap.get is O(1)
        //
        // noinspection ConstantConditions
        _moteMap.get(identifier)
                // Since we updated the object reference, we do not need to put the updated object
                // back in the map
                .merge(dto);
    }

    @Override
    public boolean isEmpty() {
        return _moteMap.isEmpty();
    }

    @Nullable
    @Override
    public Mote find(String name) {
        return _moteMap.getOrDefault(name, null);
    }

    @Override
    public List<Mote> toList() {
        return new ArrayList<>(_moteMap.values());
    }

}
