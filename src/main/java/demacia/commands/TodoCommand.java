package demacia.commands;

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
     */
    @Override
    public void execute(TaskList taskList, Terminal terminal) {
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
            // todo: change this to command error
            terminal.buffer(e.getMessage());
        }
    }
}
