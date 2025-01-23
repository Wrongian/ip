import java.lang.StringBuilder;
import java.util.ArrayList;

public class TaskList {
    private static final int MAX_TASKS = 100;
    private final ArrayList<Task> tasks;
    private int taskIndex;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.taskIndex = 0;
    }

    public void addToList(Task task) {
        // modify task array
        this.tasks.add(task);
        this.taskIndex += 1;
    }

    public int addTodo(String name) throws ArrayIndexOutOfBoundsException {
        if (taskIndex + 1 >= TaskList.MAX_TASKS) {
            throw new ArrayIndexOutOfBoundsException("Maximum tasks reached\nTask not created\nPlease delete tasks");
        }
        Todo newTodo = new Todo(name);
        addToList(newTodo);
        return this.taskIndex - 1;
    }

    public int addDeadline(String name, String by) throws IndexOutOfBoundsException {
        if (taskIndex + 1 >= TaskList.MAX_TASKS) {
            throw new ArrayIndexOutOfBoundsException("Maximum tasks reached\nTask not created\nPlease delete tasks");
        }
        Deadline newDeadline = new Deadline(name, by);
        addToList(newDeadline);
        return this.taskIndex - 1;
    }

    public int addEvent(String name, String from, String to) throws IndexOutOfBoundsException {
        if (taskIndex + 1 >= TaskList.MAX_TASKS) {
            throw new ArrayIndexOutOfBoundsException("Maximum tasks reached\nTask not created\nPlease delete tasks");
        }
        Event newEvent = new Event(name, from, to);
        addToList(newEvent);
        return this.taskIndex - 1;
    }

    public void markTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index + 1 > TaskList.MAX_TASKS) {
            throw new ArrayIndexOutOfBoundsException("Task does not exist in the list");
        }
        this.tasks.get(index).markDone();
    }

    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index + 1 > TaskList.MAX_TASKS) {
            throw new ArrayIndexOutOfBoundsException("Task does not exist in the list");
        }
        this.tasks.get(index).unmarkDone();
    }

    public String getTaskString(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index + 1 > TaskList.MAX_TASKS) {
            throw new ArrayIndexOutOfBoundsException("Task does not exist in the list");
        }
        return this.tasks.get(index).toString();
    }

    public String listTasks() {
        if (this.taskIndex == 0) {
            return "List is empty";
        }

        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < this.taskIndex; i++) {
            Task curTask = this.tasks.get(i);
            msg.append(String.valueOf(i + 1));
            msg.append(".");
            msg.append(curTask.toString());
            msg.append("\n");
        }
        // delete last newline
        if (!msg.isEmpty()) {
            msg.deleteCharAt(msg.length() - 1);
        }
        return msg.toString();
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index + 1 > TaskList.MAX_TASKS) {
            throw new ArrayIndexOutOfBoundsException("Task does not exist in the list");
        }

        this.tasks.remove(index);
        this.taskIndex -= 1;
    }
}
