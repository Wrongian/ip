package demacia.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import demacia.exceptions.InvalidSaveException;
import demacia.utils.Utils;

/**
 * Class representing a Deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructor for a Deadline object.
     *
     * @param name Name as a String for the Deadline object.
     * @param by LocalDateTime object to describe the Deadline.
     */
    public Deadline(String name, LocalDateTime by) {
        this(name, false, by);
    }

    /**
     * Constructor for a Deadline object.
     *
     * @param name Name as a String for the Deadline object.
     * @param isMarked Boolean for whether the task is already done.
     * @param by LocalDateTime object to describe the Deadline.
     */
    public Deadline(String name, boolean isMarked, LocalDateTime by) {
        super(name, isMarked);
        this.by = by;
    }

    /**
     * Constructor for a Deadline object.
     *
     * @param name Name as a String for the Deadline object.
     * @param isMarked Boolean for whether the task is already done.
     * @param by LocalDateTime object to describe the Deadline.
     * @param note String of note.
     */
    public Deadline(String name, boolean isMarked, LocalDateTime by, String note) {
        super(name, isMarked, note);
        this.by = by;
    }

    /**
     * The String representation of the Deadline object.
     *
     * @return The String representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + Utils.showDateTime(this.by)
                + ")";
    }

    /**
     * Returns the save representation of the Deadline object.
     *
     * @return The serialised Deadline object.
     */
    @Override
    public String save() {
        return super.save() + ","
                + "by:" + Utils.formatDateTime(this.by) + ",type:D";
    }

    /**
     * Loads the save file representation of the Deadline object into an
     * actual Deadline object.
     *
     * @param name The name of the Deadline object.
     * @param isMarked If the Deadline object is marked.
     * @param saveMap The arguments of the Deadline object as a HashMap.
     * @param note String of note of Deadline object.
     * @return The created Deadline object.
     * @throws InvalidSaveException If the save file representations is wrong.
     */
    public static Deadline load(
            String name, boolean isMarked,
            HashMap<String, String> saveMap, String note) throws InvalidSaveException {

        if (!saveMap.containsKey("by")) {
            throw new InvalidSaveException("Key 'by' does not exist when it is required");
        }
        String by = saveMap.get("by");
        try {
            LocalDateTime byDateTime = Utils.parseDateTime(by);
            return new Deadline(name, isMarked, byDateTime, note);
        } catch (DateTimeParseException e) {
            throw new InvalidSaveException("Datetime is formatted wrongly");
        }
    }


}
