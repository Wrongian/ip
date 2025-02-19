package demacia.commands;

import java.util.HashMap;

import demacia.exceptions.CommandException;
import demacia.exceptions.IncorrectArgumentFormatException;
import demacia.tasks.TaskList;
import demacia.ui.Terminal;
import demacia.utils.Utils;


/**
 * Class for handling the 'getnote' Command.
 */
public class GetNoteCommand extends Command {
    private final int index;

    /**
     * Constructor for creating a GetNoteCommand.
     *
     * @param index Index of the task to get the note of.
     */
    public GetNoteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the GetNoteCommand.
     *
     * @param taskList the TaskList used to execute the Command.
     * @param terminal the Terminal used to execute the Command.
     * @throws CommandException if the command fails.
     */
    @Override
    public void execute(TaskList taskList, Terminal terminal) throws CommandException {
        try {
            taskList.getTaskNote(this.index);

            terminal.buffer(taskList.getTaskNote(this.index));
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(e.getMessage());
        }
    }

    /**
     * Factory method to make a GetNoteCommand.
     *
     * @param firstArg The first argument of the command.
     * @param args The rest of the arguments as a String array.
     * @param cmds The rest of the arguments as a HashMap.
     * @return The created GetNoteCommand.
     * @throws IncorrectArgumentFormatException If the arguments are formatted incorrectly or are invalid.
     */
    public static GetNoteCommand makeCommand(
            String firstArg, String[] args, HashMap<String, String> cmds) throws IncorrectArgumentFormatException {
        if (firstArg.isEmpty() || args.length > 1) {
            throw new IncorrectArgumentFormatException(
                    "Usage: \ngetnote <task number>");
        }
        // check if int
        if (!Utils.stringIsIndex(firstArg)) {
            throw new IncorrectArgumentFormatException(
                    "Usage: \ngetnote <task number>");
        }
        return new GetNoteCommand(Integer.parseInt(firstArg) - 1);
    }
}
