package eu.telecomnancy.amio.notification.dispatchers;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import eu.telecomnancy.amio.iotlab.models.Mote;
import eu.telecomnancy.amio.iotlab.models.ConsecutiveMoteMeasuresPair;
import eu.telecomnancy.amio.iotlab.models.collections.IMoteCollection;
import eu.telecomnancy.amio.notification.contexts.EventContext;
import eu.telecomnancy.amio.notification.rules.IRule;
import eu.telecomnancy.amio.notification.rules.NewLightOnWeekEndEveningRule;
import eu.telecomnancy.amio.notification.rules.NewLightOnWeekEveningRule;
import eu.telecomnancy.amio.notification.rules.NewLightOnWeekNightRule;
import eu.telecomnancy.amio.persistence.IotLabDatabase;
import eu.telecomnancy.amio.persistence.IotLabDatabaseProvider;
import eu.telecomnancy.amio.persistence.entities.RecordAndMote;
import eu.telecomnancy.amio.polling.contexts.PollingContext;

/**
 * Rule engine wrapper handling the rules registration and the firing of all notification events
 */
public class EventDispatcher {

    /**
     * Tag under which the context passed to the rules' Facts will be registered
     *
     * @see Facts
     * @see org.jeasy.rules.annotation.Rule
     */
    public static final String ContextTag = "Context";

    /**
     * Polling context in which this event take place
     */
    private final PollingContext _context;

    /**
     * Registered rules handler
     */
    private final Rules _rules = new Rules();

    /**
     * Easy-Rules rule engine
     */
    private final RulesEngine _ruleEngine = new DefaultRulesEngine();

    /**
     * Inner list of all rules evaluated by the engine
     */
    private static final List<IRule> handledRules = Arrays.asList(
            new NewLightOnWeekEndEveningRule(),
            new NewLightOnWeekEveningRule(),
            new NewLightOnWeekNightRule()
    );

    /**
     * Default constructor
     * This will register all rules to be later evaluated on event firing
     *
     * @param context Polling context in which this event take place
     */
    public EventDispatcher(PollingContext context) {
        _context = context;

        handledRules.forEach(_rules::register);
    }

    /**
     * Evaluate the provided data against all registered rules for notification
     *
     * @param fetchedMotes A collection of all fetched motes to be passed to all rules
     * @see EventContext
     */
    public void dispatchNotificationFor(IMoteCollection fetchedMotes) {
        // Create the event context from the provided motes
        List<ConsecutiveMoteMeasuresPair> pairs
                = generateConsecutiveMoteMeasuresPairs(fetchedMotes);

        EventContext context = new EventContext(_context, fetchedMotes, pairs);

        // Create the rules payload from the context
        Facts facts = new Facts();
        facts.put(ContextTag, context);

        // Fire the event context against all the registered rules
        _ruleEngine.fire(_rules, facts);
    }

    /**
     * Generate the list of all two last records of all motes that have such records if they belong
     * to the list of provided motes
     *
     * @param fetchedMotes Motes retrieved by the application and for whom the last two values would
     *                     be retrieved
     * @return A collection of the last two records of each mote
     */
    private List<ConsecutiveMoteMeasuresPair> generateConsecutiveMoteMeasuresPairs(
            IMoteCollection fetchedMotes) {
        // Retrieve an instance of the database
        IotLabDatabase database
                = IotLabDatabaseProvider.getOrCreateInstance(_context.androidContext);

        // Generate the consecutive records pairs
        return fetchedMotes.toList()
                .stream()
                // Creating the pairs
                .map(mote -> {
                    // Retrieve the id of the mote in the database
                    long storedMoteId = database.moteDao()
                            .getByName(mote.getName()).moteId;

                    // Retrieve its two last measures
                    List<Mote> latestMotePair = database.recordDao()
                            // Getting the last two values
                            .getLatestRecordAndMotePairById(storedMoteId)
                            .stream()
                            // Converting them to a Mote that we can exploit in the
                            // IoTLab logic
                            .map(RecordAndMote::toMote)
                            // Generate the collection of those two records
                            .collect(Collectors.toList());

                    // If we do not have a pair of result to compare, we do not have enough data
                    // to perform an appropriate comparison
                    // We are then returning an null value
                    if (latestMotePair.size() != 2) {
                        return null;
                    }

                    return new ConsecutiveMoteMeasuresPair(
                            latestMotePair.get(0),
                            latestMotePair.get(1));
                })
                // Filtering the unusable values
                .filter(Objects::nonNull)
                // Generate the list of valid pairs
                .collect(Collectors.toList());
    }

}
