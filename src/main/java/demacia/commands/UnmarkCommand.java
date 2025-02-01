package demacia.commands;

import demacia.storage.SaveData;
import demacia.tasks.TaskList;
import demacia.ui.Terminal;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        // get task
        try {
            taskList.unmarkTask(this.index);
            terminal.output("Marked this task as not done yet:");
            terminal.output(taskList.getTaskString(this.index));
        } catch (IndexOutOfBoundsException e) {
            terminal.output(e.getMessage());
        }
        this.save(new SaveData(taskList));
    }
}
