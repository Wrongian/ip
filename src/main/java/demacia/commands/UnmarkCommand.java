package demacia.commands;

import demacia.storage.SaveData;
import demacia.tasks.TaskList;
import demacia.ui.Terminal;

/**
 * Class for handling the 'unmark' Command.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructor for creating an UnmarkCommand.
     *
     * @param index The index of the task to unmark in the TaskList.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the UnmarkCommand.
     *
     * @param taskList the TaskList used to execute the Command.
     * @param terminal the Terminal used to execute the Command.
     */
    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        // get task
        try {
            taskList.unmarkTask(this.index);

            terminal.buffer("Marked this task as not done yet:");
            terminal.buffer(taskList.getTaskString(this.index));
        } catch (IndexOutOfBoundsException e) {
            terminal.buffer(e.getMessage());
        }
        this.save(new SaveData(taskList));
    }
}
