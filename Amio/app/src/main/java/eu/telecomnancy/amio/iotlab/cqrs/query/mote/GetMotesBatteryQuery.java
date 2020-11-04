package eu.telecomnancy.amio.iotlab.cqrs.query.mote;

import eu.telecomnancy.amio.iotlab.Constants;

/**
 * Query to get the battery level of all motes
 */
public class GetMotesBatteryQuery extends GetMotesDataTypeQuery {

    /**
     * Create the query object and set its associated label
     */
    public GetMotesBatteryQuery() {
        super(Constants.Labels.BATTERY);
    }

}
