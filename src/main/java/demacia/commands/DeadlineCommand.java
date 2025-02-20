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
 * Class for handling the 'deadline' Command.
 */
public class DeadlineCommand extends Command {

    private final String name;
    private final LocalDateTime by;

    /**
     * Constructor for a new DeadlineCommand object.
     *
     * @param name The name of the Deadline object.
     * @param by The LocalDateTime of the deadline of the Deadline object.
     */
    public DeadlineCommand(String name, LocalDateTime by) {
        this.name = name;
        this.by = by;
    }

    /**
     * Executes the deadline Command....
     *
     * @param taskList the TaskList used to execute the Command.
     * @param terminal the Terminal used to execute the Command.
     * @throws CommandException if the command fails.
     */
    @Override
    public void execute(TaskList taskList, Terminal terminal) throws CommandException {

        try {
            int index = taskList.addDeadline(this.name, this.by);

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
     * Factory method to make a DeadlineCommand.
     *
     * @param firstArg The first argument of the command.
     * @param args The rest of the arguments as a String array.
     * @param cmds The rest of the arguments as a HashMap.
     * @return The created DeadlineCommand.
     * @throws IncorrectArgumentFormatException If the arguments are formatted incorrectly or are invalid.
     */
    public static DeadlineCommand makeCommand(
            String firstArg, String[] args, HashMap<String, String> cmds) throws IncorrectArgumentFormatException {
        if (firstArg.isEmpty() || args.length != 2 || !cmds.containsKey("by")) {
            throw new IncorrectArgumentFormatException(
                    "Usage: \ndeadline <task name> /by <deadline>");
        }
        try {
            LocalDateTime byDateTime = Utils.parseDateTime(cmds.get("by"));
            return new DeadlineCommand(firstArg, byDateTime);
        } catch (DateTimeParseException e) {
            throw new IncorrectArgumentFormatException("Date/time format error\n"
                    + "Format should be: yyyy-MM-dd HH-mm\n"
                    + "yyyy is year,"
                    + " MM is the month, dd is the day\n"
                    + "HH is the hour and mm are the minutes");
        }
    }
}
