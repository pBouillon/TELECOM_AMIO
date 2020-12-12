package eu.telecomnancy.amio.iotlab.models;

/**
 * Wrap two consecutive records of the same mote
 */
public class ConsecutiveMoteMeasuresPair {

    /**
     * The most recent record
     */
    public final Mote mostRecent;

    /**
     * The oldest record
     */
    public final Mote oldest;

    /**
     * Create a new pair from two records
     * If the mote names does not match this will throw an exception since the pair is only for
     * consecutive measures of the same mote
     *
     * @param first The first record of the pair
     * @param second The second record of the pair
     */
    public ConsecutiveMoteMeasuresPair(Mote first, Mote second) {
        // If both records does not come from the same mote, throw an exception
        if (!first.getName().equals(second.getName())) {
            throw new IllegalArgumentException(
                    "Both records should come from the same mote");
        }

        // Assign the appropriate parameters to the attributes
        if (first.getTimestamp() > second.getTimestamp()) {
            mostRecent = first;
            oldest = second;
            return;
        }

        oldest = first;
        mostRecent = second;
    }

    /**
     * Test whether or not the mote is in a room that has just been lightened
     *
     * @return true if the oldest record does not indicate a lightened room but the most recent one
     * does
     */
    public boolean isMoteRoomJustRecentlyLightened() {
        return mostRecent.getTimestamp() != oldest.getTimestamp() &&
                mostRecent.isRoomLightened() && !oldest.isRoomLightened();
    }

}
