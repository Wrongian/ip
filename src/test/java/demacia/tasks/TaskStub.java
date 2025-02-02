package demacia.tasks;

public class TaskStub extends Task {

    public TaskStub() {
        super("this is a task");
    }

    @Override
    public String toString() {
        return "[D][X] do homework (by: 2025-05-20 12:23)";
    }

    @Override
    public String save() {
        return "name:do homework,isMarked:false,by:2025-05-20 12-23,type:D";
    }

}
