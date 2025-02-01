package demacia.commands;

import demacia.ui.Terminal;
import demacia.tasks.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        String msg = taskList.listTasks();
        terminal.output(msg);
    }
}
