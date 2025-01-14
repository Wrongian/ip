import java.lang.StringBuilder;
public class TaskList {
    private static final int MAX_TASKS = 100;
    private String[] tasks;
    private int taskIndex;

    public TaskList() {
        this.tasks = new String[TaskList.MAX_TASKS];
        this.taskIndex = 0;
    }

    public void addTask(String task) {
        // modify task array
        this.tasks[taskIndex] = task;
        this.taskIndex += 1;
    }

    public String listTasks() {
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < this.taskIndex; i++) {
            msg.append(String.valueOf(i + 1));
            msg.append(". ");
            msg.append(this.tasks[i]);
            msg.append("\n");
        }
        // delete last newline
        msg.deleteCharAt(msg.length() - 1);
        return msg.toString();
    }
}
