package demacia.storage;

import demacia.exceptions.InvalidSaveException;
import demacia.tasks.TaskList;

/**
 * Class to encapsulate the data to save.
 */
public class SaveData implements Saveable {
    private TaskList taskList;

    /**
     * Constructor for the SaveData.
     *
     * @param taskList The TaskList to save.
     */
    public SaveData(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructor for the SaveData.
     *
     * @param saveString The serialised save String to process to the SaveData.
     */
    public SaveData(String saveString) {
        this.load(saveString);
    }

    /**
     * Serialises the SaveData into a String.
     * @return The serialised String.
     */
    @Override
    public String save() {
        return this.taskList.save();
    }

    /**
     * Loads the SaveData using a serialised save String.
     *
     * @param saveString The save data as a String.
     */
    public void load(String saveString) {
        try {
            this.taskList = TaskList.load(saveString);
        } catch (InvalidSaveException e) {
            System.out.println(e.getMessage());
            System.out.println("Using blank list");

            this.taskList = new TaskList();
        }
    }

    /**
     * Gets the TaskList from the SaveData.
     *
     * @return The TaskList form the SaveData.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }
}
