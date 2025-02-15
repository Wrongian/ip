package demacia.tasks;

import java.time.LocalDateTime;

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


}
