public class Todo extends Task{

    public Todo(String name) {
        this(name, false);
    }

    public Todo(String name, boolean isMarked) {
        super(name, isMarked);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String save() {
        return super.save() + ",type:T";
    }
}
