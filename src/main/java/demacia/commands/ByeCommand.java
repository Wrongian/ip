package demacia.commands;

import demacia.tasks.TaskList;
import demacia.ui.Terminal;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        terminal.output("Buybye, see ya later...");
        this.exit();
    }
}
