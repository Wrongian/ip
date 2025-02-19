package demacia.commands;

import java.util.HashMap;

import demacia.exceptions.CannotSaveException;
import demacia.exceptions.CommandException;
import demacia.exceptions.IncorrectArgumentFormatException;
import demacia.storage.SaveData;
import demacia.tasks.TaskList;
import demacia.ui.Terminal;
import demacia.utils.Utils;

/**
 * Class for handling the 'getnote' Command.
 */
public class SetNoteCommand extends Command {
    private final int index;
    private final String note;

    /**
     * Constructor for creating a GetNoteCommand.
     *
     * @param index Index of the task to set the note to.
     * @param note The String of the note to set.
     */
    public SetNoteCommand(int index, String note) {
        this.index = index;
        this.note = note;
    }

    /**
     * Executes the SetNoteCommand.
     *
     * @param taskList the TaskList used to execute the Command.
     * @param terminal the Terminal used to execute the Command.
     * @throws CommandException if the command fails.
     */
    @Override
    public void execute(TaskList taskList, Terminal terminal) throws CommandException {
        try {
            taskList.setTaskNote(this.index, this.note);

            terminal.buffer("Successfully set the note");
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(e.getMessage());
        }
        try {
            this.save(new SaveData(taskList));
        } catch (CannotSaveException e) {
            terminal.buffer(e.getMessage());
        }
    }

    /**
     * Factory method to make a SetNoteCommand.
     *
     * @param firstArg The first argument of the command.
     * @param args The rest of the arguments as a String array.
     * @param cmds The rest of the arguments as a HashMap.
     * @return The created SetNoteCommand.
     * @throws IncorrectArgumentFormatException If the arguments are formatted incorrectly or are invalid.
     */
    public static SetNoteCommand makeCommand(
            String firstArg, String[] args, HashMap<String, String> cmds) throws IncorrectArgumentFormatException {
        if (firstArg.isEmpty() || args.length != 2 || !cmds.containsKey("note")) {
            throw new IncorrectArgumentFormatException(
                    "Usage: \nsetnote <task number> /note <note>");
        }

        // check if int
        if (!Utils.stringIsIndex(firstArg)) {
            throw new IncorrectArgumentFormatException(
                    "Usage: \nsetnote <task number> /note <note>");
        }

        return new SetNoteCommand(Integer.parseInt(firstArg) - 1, cmds.get("note"));
    }
}
