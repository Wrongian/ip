package demacia.tasks;

import demacia.exceptions.InvalidSaveException;
import demacia.storage.Saveable;

import java.lang.StringBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList implements Saveable {
    private static final int MAX_TASKS = 100;
    private final ArrayList<Task> tasks;
    private int taskIndex;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.taskIndex = 0;
    }

    public void addToList(Task task) throws ArrayIndexOutOfBoundsException{
        if (taskIndex + 1 >= TaskList.MAX_TASKS) {
            throw new ArrayIndexOutOfBoundsException("Maximum tasks reached\nTask not created\nPlease delete tasks");
        }

        // modify task array
        this.tasks.add(task);
        this.taskIndex += 1;
    }

    public int addTodo(String name) throws ArrayIndexOutOfBoundsException {
        Todo newTodo = new Todo(name);
        addToList(newTodo);
        return this.taskIndex - 1;
    }

    public int addDeadline(String name, LocalDateTime by) throws IndexOutOfBoundsException {
        Deadline newDeadline = new Deadline(name, by);
        addToList(newDeadline);
        return this.taskIndex - 1;
    }

    public int addEvent(String name, LocalDateTime from, LocalDateTime to) throws IndexOutOfBoundsException {
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
            return "No tasks found";
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
        try {
            this.tasks.remove(index);
            this.taskIndex -= 1;
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Task does not exist in the list");
        }
    }

    @Override
    public String save() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.taskIndex; i++) {
            Task curTask = this.tasks.get(i);
            stringBuilder.append(curTask.save());
            stringBuilder.append("\n");
        }

        if (!stringBuilder.isEmpty()) {
            // delete last 2 chars
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        return stringBuilder.toString();
    }

    public static TaskList load(String saveString) throws InvalidSaveException {
        String[] taskStrings = saveString.split("\n");
        TaskList taskList = new TaskList();
        for (int i = 0; i < taskStrings.length; i++) {
            Task newTask = Task.load(taskStrings[i]);
            try {
                taskList.addToList(newTask);
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidSaveException("Too many tasks in the save file");
            }
        }
        return taskList;
    }

    public TaskList findTaskByString(String searchString) throws IndexOutOfBoundsException {
        TaskList taskList = new TaskList();

        for (int i = 0; i < this.taskIndex; i++) {
            Task task = this.tasks.get(i);
            if (task.nameContainsString(searchString)) {
                taskList.addToList(task);
            }
        }

        return taskList;
    }
}
