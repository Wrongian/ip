package demacia.tasks;

import java.time.LocalDateTime;

import demacia.utils.Utils;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        this(name, false, from, to);
    }

    public Event(String name, boolean isMarked, LocalDateTime from, LocalDateTime to) {
        super(name, isMarked);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + Utils.showDateTime(this.from)
                + " to: " + Utils.showDateTime(this.to)
                + ")";
    }

    @Override
    public String save() {
        return super.save()
                + ",from:" + Utils.formatDateTime(this.from)
                + ",to:" + Utils.formatDateTime(this.to)
                + ",type:";
    }
}
