package demacia.commands;

import java.util.HashMap;

import demacia.exceptions.IncorrectArgumentFormatException;
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

    /**
     * Factory method to make a ByeCommand.
     *
     * @param firstArg The first argument of the command.
     * @param args The rest of the arguments as a String array.
     * @param cmds The rest of the arguments as a HashMap.
     * @return The created ByeCommand.
     * @throws IncorrectArgumentFormatException If the arguments are formatted incorrectly or are invalid.
     */
    public static ByeCommand makeCommand(
            String firstArg, String[] args, HashMap<String, String> cmds) throws IncorrectArgumentFormatException {
        if (!firstArg.isEmpty() || args.length > 1) {
            throw new IncorrectArgumentFormatException(
                    "Usage: \nbye");
        }
        return new ByeCommand();
    }
}
