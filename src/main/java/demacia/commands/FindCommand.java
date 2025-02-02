package demacia.commands;

import demacia.tasks.TaskList;
import demacia.ui.Terminal;

public class FindCommand extends Command {
    private final String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        try {
            TaskList resultList = taskList.findTaskByString(this.searchString);
            terminal.output("Here are the matching tasks in your list:");
            terminal.output(resultList.listTasks());
        } catch (IndexOutOfBoundsException e) {
            // todo: change this to command error
            terminal.output(e.getMessage());
        }
    }
}
