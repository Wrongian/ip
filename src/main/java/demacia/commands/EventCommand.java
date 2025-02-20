package demacia.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import demacia.exceptions.CannotSaveException;
import demacia.exceptions.CommandException;
import demacia.exceptions.IncorrectArgumentFormatException;
import demacia.storage.SaveData;
import demacia.tasks.TaskList;
import demacia.ui.Terminal;
import demacia.utils.Utils;

/**
 * Class for handling the 'event' Command.
 */
public class EventCommand extends Command {

    private final String name;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructor for the EventCommand.
     *
     * @param name The name for the Event Object.
     * @param from The LocalDateTime for which the event starts.
     * @param to The LocalDateTime for which the event ends.
     */
    public EventCommand(String name, LocalDateTime from, LocalDateTime to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the EventCommand.
     *
     * @param taskList the TaskList used to execute the Command.
     * @param terminal the Terminal used to execute the Command.
     * @throws CommandException if the command fails.
     */
    @Override
    public void execute(TaskList taskList, Terminal terminal) throws CommandException {
        try {
            int index = taskList.addEvent(this.name, this.from, this.to);

            String msg = "Got it. I have added this task:\n"
                    + taskList.getTaskString(index);
            terminal.buffer(msg);


            if (taskList.getTotalTasks() == 1) {
                terminal.buffer("Now you have 1 task in the list");
            } else {
                terminal.buffer("Now you have " + String.valueOf(index + 1) + " tasks in the list");
            }

            try {
                this.save(new SaveData(taskList));
            } catch (CannotSaveException e) {
                terminal.buffer(e.getMessage());
            }

        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(e.getMessage());
        }
    }

    /**
     * Factory method to make a EventCommand.
     *
     * @param firstArg The first argument of the command.
     * @param args The rest of the arguments as a String array.
     * @param cmds The rest of the arguments as a HashMap.
     * @return The created EventCommand.
     * @throws IncorrectArgumentFormatException If the arguments are formatted incorrectly or are invalid.
     */
    public static EventCommand makeCommand(
            String firstArg, String[] args, HashMap<String, String> cmds) throws IncorrectArgumentFormatException {
        if (firstArg.isEmpty() || args.length != 3
                || !cmds.containsKey("from") || !cmds.containsKey("to")) {
            throw new IncorrectArgumentFormatException(
                    "Usage: \nevent <task name> /from <from> /to <to>");
        }
        try {
            LocalDateTime fromDateTime = Utils.parseDateTime(cmds.get("from"));
            LocalDateTime toDateTime = Utils.parseDateTime(cmds.get("to"));
            return new EventCommand(firstArg, fromDateTime, toDateTime);
        } catch (DateTimeParseException e) {
            throw new IncorrectArgumentFormatException("Date/time format error\n"
                    + "Format should be: yyyy-MM-dd HH-mm\n"
                    + "yyyy is year,"
                    + " MM is the month, dd is the day\n"
                    + "HH is the hour and mm are the minutes");
        }
    }
}
