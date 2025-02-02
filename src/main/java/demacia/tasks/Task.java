package demacia.tasks;

import demacia.exceptions.InvalidSaveException;
import demacia.storage.Saveable;
import demacia.utils.Utils;

import java.lang.StringBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

/**
 * Class to abstract methods relating to the class for other tasks to subclass.
 */
public abstract class Task implements Saveable {

    private final String name;
    private boolean isDone;

    /**
     * Constructor to create a default task.
     * @param name The name of the task as a String.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructor to create a default task.
     *
     * @param name The name of the task as a String.
     * @param isDone Boolean for whether the task is already done.
     */
    public Task(String name, boolean isDone) {
       this(name);
       this.isDone = isDone;
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
        return "name:" + this.name + ",isMarked:" + this.getIsDone();
    }

    /**
     * Loads a Task using the serialised representation of a Task.
     *
     * @param saveString The serialised representation of a Task.
     * @return The Task that from the serialised String.
     * @throws InvalidSaveException If there are any formatting errors with the save.
     */
    public static Task load(String saveString) throws InvalidSaveException{
        String[] keyValueArray = saveString.split(",");
        HashMap<String, String> saveMap = new HashMap<String, String>();
        for (int i = 0; i < keyValueArray.length; i++) {
            String[] keyValuePair = keyValueArray[i].split(":");
            if (!(keyValuePair.length == 2)) {
                throw new InvalidSaveException("Save file format is wrong");
            }
            saveMap.put(keyValuePair[0], keyValuePair[1]);
        }
        if (!saveMap.containsKey("type")) {
            throw new InvalidSaveException("Key 'type' does not exist when it is required");
        }

        String type = saveMap.get("type");

        // every task has a name
        if (!saveMap.containsKey("name")) {
            throw new InvalidSaveException("Key 'name' does not exist when it is required");
        }

        String name = saveMap.get("name");

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
            return new Todo(name);
        case "D":
            if (!saveMap.containsKey("by")) {
                throw new InvalidSaveException("Key 'by' does not exist when it is required");
            }
            String by = saveMap.get("by");
            try {
                LocalDateTime byDateTime = Utils.parseDateTime(by);
                return new Deadline(name, byDateTime);
            } catch(DateTimeParseException e) {
                throw new InvalidSaveException("Datetime is formatted wrongly");
            }
        case "E":
            if (!saveMap.containsKey("from")) {
               throw new InvalidSaveException("Key 'from' does not exist when it is required");
            }
            if (!saveMap.containsKey("to")) {
                throw new InvalidSaveException("Key 'from' does not exist when it is required");
            }

            String from = saveMap.get("from");
            String to = saveMap.get("to");
            try {
                LocalDateTime fromDateTime = Utils.parseDateTime(from);
                LocalDateTime toDateTime = Utils.parseDateTime(to);
                return new Event(name, fromDateTime, toDateTime);
            } catch(DateTimeParseException e) {
                throw new InvalidSaveException("Datetime is formatted wrongly");
            }
        }

        throw new InvalidSaveException("Save file format is wrong");
    }
}
