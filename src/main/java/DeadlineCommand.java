import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    private final String name;
    private final LocalDateTime by;

    public DeadlineCommand(String name, LocalDateTime by) {
        this.name = name;
        this.by = by;
    }
    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        try {
            int index = taskList.addDeadline(this.name, this.by);

            String msg = "Got it. I have added this task:\n" +
                    taskList.getTaskString(index);
            terminal.output(msg);
            // todo: if only one task print differently
            terminal.output("Now you have " + String.valueOf(index + 1) + " tasks in the list");

            this.save(new SaveData(taskList));
        } catch (IndexOutOfBoundsException e) {
            // todo: change this to command error
            terminal.output("Too many tasks");
        }
    }
}
