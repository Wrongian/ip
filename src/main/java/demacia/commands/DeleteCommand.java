package demacia.commands;

import demacia.storage.SaveData;
import demacia.tasks.TaskList;
import demacia.ui.Terminal;

/**
 * Class for handling the 'delete' Command.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructor for a DeleteCommand object.
     *
     * @param index The index of the Task to delete in the TaskList.
     */
    public DeleteCommand(int index) {
        this.index = index;

    }

    /**
     * Executes the DeleteCommand.
     *
     * @param taskList the TaskList used to execute the Command.
     * @param terminal the Terminal used to execute the Command.
     */
    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        try {
            String taskString = taskList.getTaskString(this.index);
            taskList.deleteTask(this.index);
            terminal.output("I have removed the task");
            terminal.output(taskString);
        } catch (IndexOutOfBoundsException e) {
            terminal.output(e.getMessage());
        }
        this.save(new SaveData(taskList));
    }
}
