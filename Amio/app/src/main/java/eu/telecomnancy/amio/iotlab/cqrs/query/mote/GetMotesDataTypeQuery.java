package eu.telecomnancy.amio.iotlab.cqrs.query.mote;

/**
 * Represent a query for a specific data on all motes
 */
public abstract class GetMotesDataTypeQuery extends MotesQuery {

    /**
     * Label of the mote's value to be queried
     */
    public final String label;

    /**
     * Default constructor
     *
     * @param label Label of the mote's value to be queried
     */
    protected GetMotesDataTypeQuery(String label) {
        this.label = label;
    }

}
