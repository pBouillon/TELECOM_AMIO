package eu.telecomnancy.amio.iotlab.dto;

import java.util.ArrayList;
import java.util.List;

import eu.telecomnancy.amio.iotlab.entities.Mote;

/**
 * Aggregator for all motes DTOs, provide an API to merge all of them into concrete entities
 */
public class MoteCollectionDtoAggregator {

    /**
     * The mote collection of the DTOs holding the brightness value
     */
    private MoteDtoCollection _brightnessCollectionDto = new MoteDtoCollection();

    /**
     * The mote collection of the DTOs holding the humidity value
     */
    private MoteDtoCollection _humidityCollectionDto = new MoteDtoCollection();

    /**
     * The mote collection of the DTOs holding the temperature value
     */
    private MoteDtoCollection _temperatureCollectionDto = new MoteDtoCollection();

    /**
     * Merge all DTOs held by this aggregator and create fully completed Motes
     * @return A collection of motes according to the data retrieved
     * @see Mote
     */
    public List<Mote> generateMotesFromAggregatedValues() {
        // TODO
        return new ArrayList<>();
    }

    /**
     * Brightness motes DTO setter
     * @param brightnessCollectionDto The mote collection of the DTOs holding the brightness value
     */
    public void setBrightnessCollectionDto(MoteDtoCollection brightnessCollectionDto) {
        _brightnessCollectionDto = brightnessCollectionDto;
    }

    /**
     * Humidity motes DTO setter
     * @param humidityCollectionDto The mote collection of the DTOs holding the humidity value
     */
    public void setHumidityCollectionDto(MoteDtoCollection humidityCollectionDto) {
        _humidityCollectionDto = humidityCollectionDto;
    }

    /**
     * Temperature motes DTO setter
     * @param temperatureCollectionDto The mote collection of the DTOs holding the temperature value
     */
    public void setTemperatureCollectionDto(MoteDtoCollection temperatureCollectionDto) {
        _temperatureCollectionDto = temperatureCollectionDto;
    }

}