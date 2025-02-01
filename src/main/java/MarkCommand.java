public class MarkCommand extends Command {

    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        // get task
        try {
            taskList.markTask(this.index);
            terminal.output("Marked this task as done:");
            terminal.output(taskList.getTaskString(this.index));
        } catch (IndexOutOfBoundsException e) {
            terminal.output(e.getMessage());
        }
        this.save(new SaveData(taskList));
    }
}
