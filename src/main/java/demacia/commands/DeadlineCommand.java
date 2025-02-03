package demacia.commands;

import java.time.LocalDateTime;

import demacia.storage.SaveData;
import demacia.tasks.TaskList;
import demacia.ui.Terminal;

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
     */
    @Override
    public void execute(TaskList taskList, Terminal terminal) {

        try {
            int index = taskList.addDeadline(this.name, this.by);

            String msg = "Got it. I have added this task:\n"
                    + taskList.getTaskString(index);
            terminal.output(msg);

            if (taskList.getTotalTasks() == 1) {
                terminal.output("Now you have 1 task in the list");
            } else {
                terminal.output("Now you have " + String.valueOf(index + 1) + " tasks in the list");
            }

            this.save(new SaveData(taskList));
        } catch (IndexOutOfBoundsException e) {
            // todo: change this to command error
            terminal.output("Too many tasks");
        }
    }
}
