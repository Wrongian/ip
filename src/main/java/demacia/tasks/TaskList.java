package demacia.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

import demacia.exceptions.InvalidSaveException;
import demacia.storage.Saveable;

/**
 * Class representing a collection to store Task objects.
 */
public class TaskList implements Saveable {
    private static final int MAX_TASKS = 100;
    private static final String NO_TASK_MESSAGE = "No tasks found";
    private static final String NOT_EXIST_MESSAGE = "Task does not exist in the list";
    private final ArrayList<Task> tasks;
    private int taskIndex;

    /**
     * Constructor to initialise the TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskIndex = 0;
    }

    /**
     * Gets the total number of tasks in the list.
     *
     * @return The total number of tasks in the list
     */
    public int getTotalTasks() {
        return this.taskIndex;
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param task The task to add to the list.
     */
    public void addToList(Task task) throws ArrayIndexOutOfBoundsException {
        if (taskIndex + 1 >= TaskList.MAX_TASKS) {
            throw new ArrayIndexOutOfBoundsException("Maximum tasks reached\nTask not created\nPlease delete tasks");
        }
        // modify task array
        this.tasks.add(task);
        this.taskIndex += 1;
    }

    /**
     * Creates and adds a Todo object to the TaskList.
     *
     * @param name The name of the Todo object to create.
     * @return The index of the newly created Todo object.
     * @throws ArrayIndexOutOfBoundsException If too many Todo objects are created.
     */
    public int addTodo(String name) throws ArrayIndexOutOfBoundsException {
        Todo newTodo = new Todo(name);
        addToList(newTodo);

        return this.taskIndex - 1;
    }

    /**
     * Creates and adds a Deadline object to the TaskList.
     *
     * @param name The name of the Deadline object to create.
     * @return The index of the newly created Deadline object.
     * @throws ArrayIndexOutOfBoundsException If too many Deadline objects are created.
     */
    public int addDeadline(String name, LocalDateTime by) throws IndexOutOfBoundsException {
        Deadline newDeadline = new Deadline(name, by);
        addToList(newDeadline);

        return this.taskIndex - 1;
    }

    /**
     * Creates and adds an Event object to the TaskList.
     *
     * @param name The name of the Event object to create.
     * @return The index of the newly created Event object.
     * @throws ArrayIndexOutOfBoundsException If too many Event objects are created.
     */
    public int addEvent(String name, LocalDateTime from, LocalDateTime to) throws IndexOutOfBoundsException {
        Event newEvent = new Event(name, from, to);
        addToList(newEvent);

        return this.taskIndex - 1;
    }

    /**
     * Marks a task in the TaskList.
     *
     * @param index The index in the TaskList to mark.
     * @throws IndexOutOfBoundsException If the Task designated by the index does not exist in the TaskList.
     */
    public void markTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index + 1 > TaskList.MAX_TASKS) {
            throw new ArrayIndexOutOfBoundsException(TaskList.NOT_EXIST_MESSAGE);
        }

        this.tasks.get(index).markDone();
    }

    /**
     * Unmarks a task in the TaskList.
     * @param index The index in the TaskList to unmark.
     * @throws IndexOutOfBoundsException If the Task designated by the index does not exist in the TaskList.
     */
    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index + 1 > TaskList.MAX_TASKS) {
            throw new ArrayIndexOutOfBoundsException(TaskList.NOT_EXIST_MESSAGE);
        }

        this.tasks.get(index).unmarkDone();
    }

    /**
     * Returns the String representation of a Task in the TaskList.
     * @param index The index of the TaskList.
     * @return The String representation of the designated Task.
     * @throws IndexOutOfBoundsException If the Task designated by the index does not exist in the TaskList.
     */
    public String getTaskString(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index + 1 > TaskList.MAX_TASKS) {
            throw new ArrayIndexOutOfBoundsException(TaskList.NOT_EXIST_MESSAGE);
        }

        return this.tasks.get(index).toString();
    }

    /**
     * Lists the tasks in the TaskList.
     *
     * @return The String representation of the all the tasks in the TaskList.
     */
    public String listTasks() {
        if (this.taskIndex == 0) {
            return TaskList.NO_TASK_MESSAGE;
        }

        StringBuilder msg = new StringBuilder();

        for (int i = 0; i < this.taskIndex; i++) {
            Task curTask = this.tasks.get(i);
            msg.append(String.valueOf(i + 1));
            msg.append(". ");
            msg.append(curTask.toString());
            msg.append("\n");
        }

        // delete last newline
        if (!msg.isEmpty()) {
            msg.deleteCharAt(msg.length() - 1);
        }
        return msg.toString();
    }

    /**
     * Deletes a task in the TaskList.
     * @param index The index of the Task in the TaskList to delete.
     * @throws IndexOutOfBoundsException If the Task designated by the index does not exist in the TaskList.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index + 1 > TaskList.MAX_TASKS) {
            throw new ArrayIndexOutOfBoundsException(TaskList.NOT_EXIST_MESSAGE);
        }

        try {
            this.tasks.remove(index);
            this.taskIndex -= 1;

            assert(this.taskIndex >= 0);
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException(TaskList.NOT_EXIST_MESSAGE);
        }
    }

    /**
     * Returns the serialised String representation of the tasks in the TaskList for saving.
     *
     * @return The serialised String representation of the tasks in the TaskList.
     */
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

    /**
     * Loads the tasks into the TaskList using a serialised save String.
     * @param saveString The save String to load.
     * @return The TaskList from the String.
     * @throws InvalidSaveException If the save format is invalid.
     */
    public static TaskList load(String saveString) throws InvalidSaveException {
        String[] taskStrings = saveString.split("\n");

        TaskList taskList = new TaskList();

        for (String taskString : taskStrings) {
            Task newTask = Task.load(taskString);
            try {
                taskList.addToList(newTask);
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidSaveException("Too many tasks in the save file");
            }
        }
        return taskList;
    }

    /**
     * Finds a Task in the TaskList by a String.
     *
     * @param searchString The String to search for a task.
     * @return TaskList of found tasks.
     * @throws IndexOutOfBoundsException If the found taskList has more than the maximum allowed number of tasks.
     */
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