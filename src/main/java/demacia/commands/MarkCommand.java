package demacia.commands;

import java.util.HashMap;

import demacia.exceptions.CommandException;
import demacia.exceptions.IncorrectArgumentFormatException;
import demacia.storage.SaveData;
import demacia.tasks.TaskList;
import demacia.ui.Terminal;
import demacia.utils.Utils;

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
     * @throws CommandException if the command fails.
     */
    @Override
    public void execute(TaskList taskList, Terminal terminal) throws CommandException {
        // get task
        try {
            taskList.markTask(this.index);

            terminal.buffer("Marked this task as done:");
            terminal.buffer(taskList.getTaskString(this.index));
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(e.getMessage());
        }
        this.save(new SaveData(taskList));
    }

    /**
     * Factory method to make a MarkCommand.
     *
     * @param firstArg The first argument of the command.
     * @param args The rest of the arguments as a String array.
     * @param cmds The rest of the arguments as a HashMap.
     * @return The created MarkCommand.
     * @throws IncorrectArgumentFormatException If the arguments are formatted incorrectly or are invalid.
     */
    public static MarkCommand makeCommand(
            String firstArg, String[] args, HashMap<String, String> cmds) throws IncorrectArgumentFormatException {
        if (firstArg.isEmpty() || args.length > 1) {
            throw new IncorrectArgumentFormatException(
                    "Usage: \nmark <task number>");
        }
        // check if int
        if (!Utils.stringIsIndex(firstArg)) {
            throw new IncorrectArgumentFormatException(
                    "Usage: \nmark <task number>");
        }

        return new MarkCommand(Integer.parseInt(firstArg) - 1);
    }
}
