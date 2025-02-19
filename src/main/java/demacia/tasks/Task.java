package demacia.tasks;

import java.util.HashMap;

import demacia.exceptions.InvalidSaveException;
import demacia.storage.Saveable;

/**
 * Class to abstract methods relating to the class for other tasks to subclass.
 */
public abstract class Task implements Saveable {

    private final String name;
    private boolean isDone;
    private String note;

    /**
     * Constructor to create a default task.
     * @param name The name of the task as a String.
     */
    public Task(String name) {
        this(name, false);
    }

    /**
     * Constructor to create a default task.
     *
     * @param name The name of the task as a String.
     * @param isDone Boolean for whether the task is already done.
     */
    public Task(String name, boolean isDone) {
        this(name, isDone, "");
    }

    /**
     * Constructor to create a default task.
     *
     * @param name The name of the task as a String.
     * @param isDone Boolean for whether the task is already done.
     * @param note String for the note.
     */
    public Task(String name, boolean isDone, String note) {
        this.name = name;
        this.isDone = isDone;
        this.note = note;
    }

    /**
     * Gets the name of the Task as a String.
     *
     * @return Name of the task as a String.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Gets whether the task is done or not done as a boolean.
     *
     * @return Boolean for whether the task is done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Sets whether the task is done.
     *
     * @param isDone The status to set the task(done or not).
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Setter for the note String.
     *
     * @param note The note String.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Getting for the note String
     *
     * @return The note string.
     */
    public String getNote() {
        return this.note;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return The String representation of the task.
     */
    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();

        if (this.getIsDone()) {
            msg.append("[X] ");
        } else {
            msg.append("[ ] ");
        }
        msg.append(this.getName());

        return msg.toString();
    }

    /**
     * Returns the serialised String representation of the task for saving.
     *
     * @return Serialised String representation of the task for saving.
     */
    @Override
    public String save() {
        if (!this.note.isEmpty()) {
            return "name:" + this.name + ",isMarked:"
                    + this.getIsDone() + ",note:" + this.note;
        } else {
            return "name:" + this.name + ",isMarked:"
                    + this.getIsDone();
        }

    }

    /**
     * Loads a Task using the serialised representation of a Task.
     *
     * @param saveString The serialised representation of a Task.
     * @return The Task that from the serialised String.
     * @throws InvalidSaveException If there are any formatting errors with the save.
     */
    public static Task load(String saveString) throws InvalidSaveException {
        String[] keyValueArray = saveString.split(",");

        HashMap<String, String> saveMap = Task.buildSaveMap(keyValueArray);

        if (!saveMap.containsKey("type")) {
            throw new InvalidSaveException("Key 'type' does not exist when it is required");
        }

        String type = saveMap.get("type");

        // every task has a name
        if (!saveMap.containsKey("name")) {
            throw new InvalidSaveException("Key 'name' does not exist when it is required");
        }

        String name = saveMap.get("name");

        String note;
        if (saveMap.containsKey("note")) {
            note = saveMap.get("note");
        } else {
            note = "";
        }


        // assume if the key "isMarked" not in the saveMap its false
        boolean isMarked = false;
        // default false if not true
        if (saveMap.containsKey("isMarked")) {
            if (saveMap.get("isMarked").equals("true")) {
                isMarked = true;
            }
        }

        switch (type) {
        case "T":
            return Todo.load(name, isMarked, note);
        case "D":
            return Deadline.load(name, isMarked, saveMap, note);
        case "E":
            return Event.load(name, isMarked, saveMap, note);
        default:
            throw new InvalidSaveException("Save file format is wrong");
        }
    }

    /**
     * Builds a HashMap based on the key value pairs in the save file.
     * @param keyValueArray The key value pairs in the save file.
     * @return The HashMap based on the key value pairs in the save file.
     * @throws InvalidSaveException If the format of the key value pairs in the save file are wrong.
     */
    private static HashMap<String, String> buildSaveMap(
            String[] keyValueArray) throws InvalidSaveException {

        HashMap<String, String> saveMap = new HashMap<>();

        for (String s : keyValueArray) {
            String[] keyValuePair = s.split(":");
            if (!(keyValuePair.length == 2)) {
                throw new InvalidSaveException("Save file format is wrong");
            }
            saveMap.put(keyValuePair[0], keyValuePair[1]);
        }

        return saveMap;
    }

    /**
     * Checks if the name of the Todo contains a String.
     * @param searchString The string to check.
     * @return Boolean indicating if the name of the Todo contains a String.
     */
    public boolean nameContainsString(String searchString) {
        return this.name.contains(searchString);
    }
}
