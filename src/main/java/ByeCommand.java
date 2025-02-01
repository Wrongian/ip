public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Terminal terminal) {
        terminal.output("Buybye, see ya later...");
        this.exit();
    }
}
