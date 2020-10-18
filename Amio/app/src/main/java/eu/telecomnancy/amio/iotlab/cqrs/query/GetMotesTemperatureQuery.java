package eu.telecomnancy.amio.iotlab.cqrs.query;

/**
 * Query to get the temperature of all motes within the desired range
 */
public class GetMotesTemperatureQuery extends MotesQuery {

    /**
     * Default constructor
     * @param numberOfQueriedMotes Number of queried motes
     */
    public GetMotesTemperatureQuery(int numberOfQueriedMotes) {
        super(numberOfQueriedMotes);
    }

}
