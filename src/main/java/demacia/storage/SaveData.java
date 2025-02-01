package demacia.storage;

import demacia.tasks.TaskList;
import demacia.exceptions.InvalidSaveException;

public class SaveData implements Saveable {
    private TaskList taskList;

    public SaveData(TaskList taskList) {
        this.taskList = taskList;
    }

    public SaveData(String saveString) {
        this.load(saveString);
    }

    @Override
    public String save() {
        return this.taskList.save();
    }

    public void load(String saveString) {
        try {
            this.taskList = TaskList.load(saveString);
        } catch (InvalidSaveException e) {
            System.out.println(e.getMessage());
            System.out.println("Using blank list");
            this.taskList = new TaskList();
        }
    }

    public TaskList getTaskList() {
        return this.taskList;
    }
}
