package demacia.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import demacia.exceptions.InvalidSaveException;
import demacia.utils.Utils;

/**
 * Class to represent an event.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructor to create an Event object.
     *
     * @param name Name as a String for the Event object.
     * @param from LocalDateTime object to represent when the event starts.
     * @param to LocalDateTime object to represent when the event ends.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        this(name, false, from, to);
    }

    /**
     * Constructor to create an Event object.
     *
     * @param name Name as a String for the Event object.
     * @param isMarked Boolean for whether the task is already done.
     * @param from LocalDateTime object to represent when the event starts.
     * @param to LocalDateTime object to represent when the event ends.
     */
    public Event(String name, boolean isMarked, LocalDateTime from, LocalDateTime to) {
        super(name, isMarked);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor to create an Event object.
     *
     * @param name Name as a String for the Event object.
     * @param isMarked Boolean for whether the task is already done.
     * @param from LocalDateTime object to represent when the event starts.
     * @param to LocalDateTime object to represent when the event ends.
     * @param note String of note.
     */
    public Event(String name, boolean isMarked, LocalDateTime from, LocalDateTime to, String note) {
        super(name, isMarked, note);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the String representation of the Event object.
     *
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + Utils.showDateTime(this.from)
                + " to: " + Utils.showDateTime(this.to)
                + ")";
    }

    /**
     * Returns the serialised representation of the Event object as a String for saving.
     *
     * @return Serialsed representation of the Event object as a String.
     */
    @Override
    public String save() {
        return super.save()
                + ",from:" + Utils.formatDateTime(this.from)
                + ",to:" + Utils.formatDateTime(this.to)
                + ",type:";
    }

    /**
     * Loads the save file representation of the Event object into an
     * actual Event object.
     *
     * @param name The name of the Event object.
     * @param isMarked If the Event object is marked.
     * @param saveMap The arguments of the Event object as a HashMap.
     * @param note The String of the note.
     * @return The created Event object.
     * @throws InvalidSaveException If the save file representations is wrong.
     */
    public static Event load(
            String name, boolean isMarked,
            HashMap<String, String> saveMap, String note) throws InvalidSaveException {

        if (!saveMap.containsKey("from")) {
            throw new InvalidSaveException("Key 'from' does not exist when it is required");
        }
        if (!saveMap.containsKey("to")) {
            throw new InvalidSaveException("Key 'from' does not exist when it is required");
        }

        String from = saveMap.get("from");
        String to = saveMap.get("to");

        try {
            LocalDateTime fromDateTime = Utils.parseDateTime(from);
            LocalDateTime toDateTime = Utils.parseDateTime(to);

            return new Event(name, isMarked, fromDateTime, toDateTime, note);
        } catch (DateTimeParseException e) {
            throw new InvalidSaveException("Datetime is formatted wrongly");
        }
    }
}
