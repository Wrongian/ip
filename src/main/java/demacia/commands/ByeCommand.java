package demacia.commands;

import demacia.tasks.TaskList;
import demacia.ui.Terminal;

/**
 * Class for handling the 'bye' command
 */
public class ByeCommand extends Command {

    /**
     * Executes the 'bye' command.
     *
     * @param taskList the TaskList to be used to execute the command.
     * @param terminal the Terminal to be used to execute the command.
     */
    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        terminal.buffer("Buybye, see ya later...");
        this.exit();
    }
}
