package eu.telecomnancy.amio.iotlab.cqrs.query;

/**
 * Query to get the brightness of all motes within the desired range
 */
public class GetMotesBrightnessQuery extends MotesQuery {

    /**
     * Default constructor
     * @param numberOfQueriedMotes Number of queried motes
     */
    public GetMotesBrightnessQuery(int numberOfQueriedMotes) {
        super(numberOfQueriedMotes);
    }

}