public class Deadline extends Task {
    private final String by;

    public Deadline(String name, String by) {
        this(name, false, by);
    }

    public Deadline(String name, boolean isMarked, String by) {
       super(name, isMarked);
       this.by = by;
    }

    @Override
    public String toString() {
       return "[D]" + super.toString() +
               " (by: " + this.by +
               ")";
    }

    @Override
    public String save() {
        return super.save() + "," +
                "by:" + this.by + ",type:D";
    }


}
