import java.lang.StringBuilder;
public class TaskList {
    private static final int MAX_TASKS = 100;
    private final Task[] tasks;
    private int taskIndex;

    public TaskList() {
        this.tasks = new Task[TaskList.MAX_TASKS];
        this.taskIndex = 0;
    }

    public void addToList(Task task) {
        // modify task array
        this.tasks[taskIndex] = task;
        this.taskIndex += 1;
    }


    public int addTodo(String name) {
        // todo: check to see if exceeds max number of tasks
        Todo newTodo = new Todo(name);
        addToList(newTodo);
        return this.taskIndex - 1;
    }

    public int addDeadline(String name, String by) {
        // todo: check to see if exceeds max number of tasks
        Deadline newDeadline = new Deadline(name, by);
        addToList(newDeadline);
        return this.taskIndex - 1;
    }

    public int addEvent(String name, String from, String to) {
        // todo: check to see if exceeds max number of tasks
        Event newEvent = new Event(name, from, to);
        addToList(newEvent);
        return this.taskIndex - 1;
    }

    public void markTask(int index) {
        // todo: check to see if index in the range
        this.tasks[index].markDone();
    }

    public void unmarkTask(int index) {
        // todo: check to see if index in the range
        this.tasks[index].unmarkDone();
    }

    public String getTaskString(int index) {
        // todo: check to see if index in the range
        return this.tasks[index].toString();
    }

    public String listTasks() {
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < this.taskIndex; i++) {
            Task curTask = this.tasks[i];
            msg.append(String.valueOf(i + 1));
            msg.append(".");
            msg.append(curTask.toString());
            msg.append("\n");
        }
        // delete last newline
        msg.deleteCharAt(msg.length() - 1);
        return msg.toString();
    }
}
