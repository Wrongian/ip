package demacia.commands;

import demacia.tasks.TaskList;
import demacia.ui.Terminal;

/**
 * Class for handling the 'list' Command.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand.
     *
     * @param taskList the TaskList used to execute the Command.
     * @param terminal the Terminal used to execute the Command.
     */
    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        String msg = taskList.listTasks();
        terminal.buffer(msg);
    }
}
