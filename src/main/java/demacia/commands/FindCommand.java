package demacia.commands;

import demacia.tasks.TaskList;
import demacia.ui.Terminal;

/**
 * Class for handling the 'find' command.
 */
public class FindCommand extends Command {
    private final String searchString;

    /**
     * Constructor for creating a FindCommand.
     *
     * @param searchString String used to search for Tasks.
     */
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Executes the FindCommand.
     *
     * @param taskList the TaskList used to execute the Command.
     * @param terminal the Terminal used to execute the Command.
     */
    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        try {
            TaskList resultList = taskList.findTaskByString(this.searchString);
            terminal.buffer("Find results:");
            terminal.buffer(resultList.listTasks());
        } catch (IndexOutOfBoundsException e) {
            // todo: change this to command error
            terminal.buffer(e.getMessage());
        }
    }
}
