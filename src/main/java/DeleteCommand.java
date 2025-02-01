public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;

    }
    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        try {
            String taskString = taskList.getTaskString(this.index);
            taskList.deleteTask(this.index);
            terminal.output("I have removed the task");
            terminal.output(taskString);
        } catch (IndexOutOfBoundsException e) {
            terminal.output(e.getMessage());
        }
        this.save(new SaveData(taskList));
    }
}
