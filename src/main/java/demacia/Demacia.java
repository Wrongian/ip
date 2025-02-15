package demacia;

import demacia.commands.Command;
import demacia.exceptions.IncorrectArgumentFormatException;
import demacia.storage.SaveData;
import demacia.storage.SaveHandler;
import demacia.tasks.TaskList;
import demacia.ui.Terminal;

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
     * Gets the greeting for the chatbot.
     */
    public String getGreeting() {
        this.terminal.buffer("Hello I am Demacia, a chatbot");
        this.terminal.buffer("Type what you desire");
        return this.terminal.getOutput();
    }

    /**
     * Gets a response from the bot based on an input.
     *
      * @param input The input to the bot to elicit a response.
     */
    public DemaciaResponse getResponse(String input) {
        // get messages from user
        boolean isExit = false;
        try {
            // get command
            Command cmd = Parser.parseCommand(input);

            // execute command
            cmd.execute(this.taskList, this.terminal);
            isExit = cmd.getIsExit();
        } catch (IncorrectArgumentFormatException e) {
            this.terminal.buffer(e.getMessage());
        } finally {
            return new DemaciaResponse(this.terminal.getOutput(), isExit);
        }
    }
}
