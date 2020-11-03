package eu.telecomnancy.amio.iotlab.cqrs.query.mote;

import eu.telecomnancy.amio.iotlab.Constants;

/**
 * Query to get the humidity of all motes
 */
public class GetMotesHumidityQuery extends GetMotesDataTypeQuery {

    /**
     * Create the query object and set its associated label
     */
    public GetMotesHumidityQuery() {
        super(Constants.Labels.HUMIDITY);
    }

}
