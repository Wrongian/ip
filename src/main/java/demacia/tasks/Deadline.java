package demacia.tasks;

import demacia.utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String name, LocalDateTime by) {
        this(name, false, by);
    }

    public Deadline(String name, boolean isMarked, LocalDateTime by) {
       super(name, isMarked);
       this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
               " (by: " + Utils.showDateTime(this.by) +
               ")";
    }

    @Override
    public String save() {
        return super.save() + "," +
                "by:" + Utils.formatDateTime(this.by) + ",type:D";
    }


}
