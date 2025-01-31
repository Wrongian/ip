public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String name, String from, String to) {
        this(name, false, from, to);
    }

    public Event(String name, boolean isMarked, String from, String to) {
        super(name, isMarked);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + this.from +
                " to: " + this.to +
                ")";
    }

    @Override
    public String save() {
        return super.save() +
                ",from:" + this.from +
                ",to:" + this.to +
                ",type:";
    }
}
