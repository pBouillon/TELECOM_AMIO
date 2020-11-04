package eu.telecomnancy.amio.iotlab.cqrs.query.mote;

import eu.telecomnancy.amio.iotlab.Constants;

/**
 * Query to get the brightness of all motes
 */
public class GetMotesBrightnessQuery extends GetMotesDataTypeQuery {

    /**
     * Create the query object and set its associated label
     */
    public GetMotesBrightnessQuery() {
        super(Constants.Labels.BRIGHTNESS);
    }

}
