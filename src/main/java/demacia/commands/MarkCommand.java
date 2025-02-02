package demacia.commands;

import demacia.storage.SaveData;
import demacia.tasks.TaskList;
import demacia.ui.Terminal;

/**
 * Class for handling the 'mark' Command.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructor for creating a MarkCommand.
     *
     * @param index The index of the task in the TaskList to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the MarkCommand.
     *
     * @param taskList the TaskList used to execute the Command.
     * @param terminal the Terminal used to execute the Command.
     */
    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        // get task
        try {
            taskList.markTask(this.index);
            terminal.output("Marked this task as done:");
            terminal.output(taskList.getTaskString(this.index));
        } catch (IndexOutOfBoundsException e) {
            terminal.output(e.getMessage());
        }
        this.save(new SaveData(taskList));
    }
}
