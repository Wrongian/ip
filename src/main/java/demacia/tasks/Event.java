package demacia.tasks;

import demacia.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
     * Returns the String representation of the Event object.
     *
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + Utils.showDateTime(this.from) +
                " to: " + Utils.showDateTime(this.to) +
                ")";
    }

    /**
     * Returns the serialised representation of the Event object as a String for saving.
     *
     * @return Serialsed representation of the Event object as a String.
     */
    @Override
    public String save() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH/mm");
        return super.save() +
                ",from:" + Utils.formatDateTime(this.from) +
                ",to:" + Utils.formatDateTime(this.to) +
                ",type:";
    }
}
