package demacia;

import demacia.commands.Command;
import demacia.storage.SaveData;
import demacia.storage.SaveHandler;
import demacia.tasks.TaskList;
import demacia.ui.Terminal;
import demacia.exceptions.IncorrectArgumentFormatException;

/**
 * Class to represent the chatbot.
 */
public class Demacia {
    private final Terminal terminal;
    private TaskList taskList;

    /**
     * Constructor to create the chatbot.
     */
    public Demacia() {
        this.terminal = new Terminal();
        this.taskList = new TaskList();

        SaveData saveData = SaveHandler.load();
        this.taskList = saveData.getTaskList();
   }

    /**
     * Greets the user.
     */
    public void greet() {
        this.terminal.printHorizontal();
        this.terminal.output("Hello I am Demacia, a chatbot");
        this.terminal.output("Type what you desire");
        this.terminal.printHorizontal();
    }

    /**
     * Starts the bot and runs an infinite loop until a command exits.
     */
    public void start() {
        // get messages from user
        // todo: enums for the commands
        // todo: higher order function so that dont have to add horizontal lines to everything
        boolean isExit = false;
        while (!isExit) {
            // get command
            String msg = this.terminal.input();

            this.terminal.printHorizontal();
            try {
                Command cmd = Parser.parseCommand(msg);
                cmd.execute(this.taskList, this.terminal);
                isExit = cmd.getIsExit();
            } catch (IncorrectArgumentFormatException e) {
                this.terminal.output(e.getMessage());
            } finally {
                this.terminal.printHorizontal();
            }
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        Demacia bot = new Demacia();
        bot.greet();
        bot.start();
    }
}
