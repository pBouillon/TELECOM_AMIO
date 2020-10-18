package eu.telecomnancy.amio.iotlab.cqrs.query;

/**
 * Generic query for all queries querying a specific number of motes
 */
abstract class MotesQuery {

    /**
     * Number of queried motes
     */
    private int _numberOfQueriedMotes;

    /**
     * Default constructor
     * @param numberOfQueriedMotes Number of queried motes
     */
    protected MotesQuery(int numberOfQueriedMotes) {
        _numberOfQueriedMotes = numberOfQueriedMotes;
    }

    /**
     * Getter for the number of queried motes
     * @return The number of queried motes
     */
    public int getNumberOfQueriedMotes() {
        return _numberOfQueriedMotes;
    }

}
