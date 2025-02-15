package demacia.commands;

import java.util.HashMap;

import demacia.exceptions.IncorrectArgumentFormatException;
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

    /**
     * Factory method to make a FindCommand.
     *
     * @param firstArg The first argument of the command.
     * @param args The rest of the arguments as a String array.
     * @param cmds The rest of the arguments as a HashMap.
     * @return The created FindCommand.
     * @throws IncorrectArgumentFormatException If the arguments are formatted incorrectly or are invalid.
     */
    public static FindCommand makeCommand(
            String firstArg, String[] args, HashMap<String, String> cmds) throws IncorrectArgumentFormatException {
        if (firstArg.isEmpty() || args.length > 1) {
            throw new IncorrectArgumentFormatException(
                    "Usage: \nfind <text to search for>");
        }
        return new FindCommand(firstArg);
    }
}
