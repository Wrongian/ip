package demacia.commands;

import java.util.HashMap;

import demacia.exceptions.CommandException;
import demacia.exceptions.IncorrectArgumentFormatException;
import demacia.storage.SaveData;
import demacia.tasks.TaskList;
import demacia.ui.Terminal;
import demacia.utils.Utils;

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
     * @throws CommandException if the command fails.
     */
    @Override
    public void execute(TaskList taskList, Terminal terminal) throws CommandException {
        try {
            String taskString = taskList.getTaskString(this.index);

            taskList.deleteTask(this.index);

            terminal.buffer("I have removed the task");
            terminal.buffer(taskString);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(e.getMessage());
        }

        this.save(new SaveData(taskList));
    }

    /**
     * Factory method to make a DeleteCommand.
     *
     * @param firstArg The first argument of the command.
     * @param args The rest of the arguments as a String array.
     * @param cmds The rest of the arguments as a HashMap.
     * @return The created DeleteCommand.
     * @throws IncorrectArgumentFormatException If the arguments are formatted incorrectly or are invalid.
     */
    public static DeleteCommand makeCommand(
            String firstArg, String[] args, HashMap<String, String> cmds) throws IncorrectArgumentFormatException {
        if (firstArg.isEmpty() || args.length > 1) {
            throw new IncorrectArgumentFormatException(
                    "Usage: \ndelete <task number>");
        }
        // check if int
        if (!Utils.stringIsIndex(firstArg)) {
            throw new IncorrectArgumentFormatException(
                    "Usage: \nmark <task number>");
        }

        return new DeleteCommand(Integer.parseInt(firstArg) - 1);
    }
}
