public class TodoCommand extends Command {

    private final String name;

    public TodoCommand(String name) {
        this.name = name;

    }

    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        try {
            int index = taskList.addTodo(this.name);

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
