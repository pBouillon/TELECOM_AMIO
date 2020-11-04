package eu.telecomnancy.amio.iotlab.cqrs.query.mote;

import eu.telecomnancy.amio.iotlab.Constants;

/**
 * Query to get the temperature of all motes
 */
public class GetMotesTemperatureQuery extends GetMotesDataTypeQuery {

    /**
     * Create the query object and set its associated label
     */
    public GetMotesTemperatureQuery() {
        super(Constants.Labels.TEMPERATURE);
    }

}
