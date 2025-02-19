package demacia.commands;

import java.util.HashMap;

import demacia.exceptions.CommandException;
import demacia.exceptions.IncorrectArgumentFormatException;
import demacia.storage.SaveData;
import demacia.tasks.TaskList;
import demacia.ui.Terminal;

/**
 * Class for handling the 'todo' Command.
 */
public class TodoCommand extends Command {

    private final String name;

    /**
     * Constructor for creating a TodoCommand.
     *
     * @param name The name of the todo to be created.
     */
    public TodoCommand(String name) {
        this.name = name;

    }

    /**
     * Executes the TodoCommand.
     *
     * @param taskList the TaskList used to execute the Command.
     * @param terminal the Terminal used to execute the Command.
     * @throws CommandException if the command fails.
     */
    @Override
    public void execute(TaskList taskList, Terminal terminal) throws CommandException {
        try {
            int index = taskList.addTodo(this.name);

            String msg = "Got it. I have added this task:\n"
                    + taskList.getTaskString(index);
            terminal.buffer(msg);

            if (taskList.getTotalTasks() == 1) {
                terminal.buffer("Now you have 1 task in the list");
            } else {
                terminal.buffer("Now you have " + String.valueOf(index + 1) + " tasks in the list");
            }

            this.save(new SaveData(taskList));
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(e.getMessage());
        }
    }

    /**
     * Factory method to make a TodoCommand.
     *
     * @param firstArg The first argument of the command.
     * @param args The rest of the arguments as a String array.
     * @param cmds The rest of the arguments as a HashMap.
     * @return The created TodoCommand.
     * @throws IncorrectArgumentFormatException If the arguments are formatted incorrectly or are invalid.
     */
    public static TodoCommand makeCommand(
            String firstArg, String[] args, HashMap<String, String> cmds) throws IncorrectArgumentFormatException {
        if (firstArg.isEmpty() || args.length > 1) {
            throw new IncorrectArgumentFormatException(
                    "Usage: \ntodo <task name>");
        }
        return new TodoCommand(firstArg);
    }
}
