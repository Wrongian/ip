package demacia.commands;

import java.util.HashMap;

import demacia.exceptions.IncorrectArgumentFormatException;
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

    /**
     * Factory method to make a ListCommand.
     *
     * @param firstArg The first argument of the command.
     * @param args The rest of the arguments as a String array.
     * @param cmds The rest of the arguments as a HashMap.
     * @return The created ListCommand.
     * @throws IncorrectArgumentFormatException If the arguments are formatted incorrectly or are invalid.
     */
    public static ListCommand makeCommand(
            String firstArg, String[] args, HashMap<String, String> cmds) throws IncorrectArgumentFormatException {
        if (!firstArg.isEmpty() || args.length > 1) {
            throw new IncorrectArgumentFormatException("Usage: \nlist");
        }
        return new ListCommand();
    }
}
