import java.time.LocalDateTime;

public class EventCommand extends Command {

    private final String name;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventCommand(String name, LocalDateTime from, LocalDateTime to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        try {
            int index = taskList.addEvent(this.name, this.from, this.to);
            String msg = "Got it. I have added this task:\n" +
                    taskList.getTaskString(index);
            terminal.output(msg);
            // todo: if only one task print differently
            terminal.output("Now you have " + String.valueOf(index + 1) + " tasks in the list");
            this.save(new SaveData(taskList));

        } catch (IndexOutOfBoundsException e) {
            // todo: change this to command error
            terminal.output(e.getMessage());
        }
    }
}
