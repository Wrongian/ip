package demacia.commands;

import java.time.LocalDateTime;

import demacia.storage.SaveData;
import demacia.tasks.TaskList;
import demacia.ui.Terminal;

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
     */
    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        try {
            int index = taskList.addEvent(this.name, this.from, this.to);

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
            terminal.output(e.getMessage());
        }
    }
}
