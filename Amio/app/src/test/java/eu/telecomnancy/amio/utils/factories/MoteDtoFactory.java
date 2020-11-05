package eu.telecomnancy.amio.utils.factories;

import java.util.Date;

import eu.telecomnancy.amio.iotlab.Constants;
import eu.telecomnancy.amio.iotlab.dto.MoteDto;

/**
 * Factory to generate MoteDto fixtures
 */
public final class MoteDtoFactory extends FactoryBase<MoteDto> {

    @Override
    public MoteDto generate() {
        long currentTime = new Date().getTime();

        MoteDto dto = new MoteDto();

        dto.mote = String.valueOf(currentTime);
        dto.timestamp = currentTime;
        dto.label = Constants.Labels.BRIGHTNESS;
        dto.value = currentTime;

        return dto;
    }

}
