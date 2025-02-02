package demacia.tasks;

/**
 * Class representing a stub for the Task.
 */
public class TaskStub extends Task {

    /**
     * Constructor for the Task stub.
     */
    public TaskStub() {
        super("this is a task");
    }

    /**
     * Returns sample String representation of a Task.
     *
     * @return Sample String representation of a Task.
     */
    @Override
    public String toString() {
        return "[D][X] do homework (by: 2025-05-20 12:23)";
    }

    /**
     * Returns sample Save String representation of a Task.
     *
     * @return Sample String representation of a Task.
     */
    @Override
    public String save() {
        return "name:do homework,isMarked:false,by:2025-05-20 12-23,type:D";
    }

}
