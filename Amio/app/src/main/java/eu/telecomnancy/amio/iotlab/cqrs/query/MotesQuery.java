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
     * @param numberOfQueriedMotes Number of queried motes, should be more than 0
     */
    protected MotesQuery(int numberOfQueriedMotes) {
        if (numberOfQueriedMotes <= 0) {
            throw new IllegalArgumentException(
                    "The number of queried mote should be more than 0 " +
                            "(actual: " + numberOfQueriedMotes + ")");
        }

        _numberOfQueriedMotes = numberOfQueriedMotes;
    }

    /**
     * Getter for the number of queried motes
     * @return The number of queried motes, guaranteed to be more than 0
     */
    public int getNumberOfQueriedMotes() {
        return _numberOfQueriedMotes;
    }

}
