package demacia.commands;

import demacia.tasks.TaskList;
import demacia.ui.Terminal;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        String msg = taskList.listTasks();
        terminal.output(msg);
    }
}
