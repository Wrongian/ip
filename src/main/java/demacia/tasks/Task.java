package demacia.tasks;

import demacia.exceptions.InvalidSaveException;
import demacia.storage.Saveable;
import demacia.utils.Utils;

import java.lang.StringBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public abstract class Task implements Saveable {

    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
       this(name);
       this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

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

    @Override
    public String save() {
        return "name:" + this.name + ",isMarked:" + this.getIsDone();
    }

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

    public boolean nameContainsString(String searchString) {
        return this.name.contains(searchString);
    }
}
