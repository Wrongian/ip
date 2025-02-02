package demacia.tasks;

/**
 * Class to represent a Todo.
 */
public class Todo extends Task {

    /**
     * Constructor to create a Todo object.
     *
     * @param name The name of the Todo to create.
     */
    public Todo(String name) {
        this(name, false);
    }

    /**
     * Constructor to create a Todo object.
     *
     * @param name The name of the Todo to create.
     * @param isMarked Boolean if the Todo is Done.
     */
    public Todo(String name, boolean isMarked) {
        super(name, isMarked);
    }

    /**
     * Returns String representation of the Todo object.
     *
     * @return String representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the serialised save String of the Todo object.
     *
     * @return Serialised save String representation of the Todo object.
     */
    @Override
    public String save() {
        return super.save() + ",type:T";
    }
}
