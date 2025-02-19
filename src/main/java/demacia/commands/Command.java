package demacia.commands;

import demacia.exceptions.CommandException;
import demacia.storage.SaveData;
import demacia.storage.SaveHandler;
import demacia.tasks.TaskList;
import demacia.ui.Terminal;

/**
 * The abstract superclass use to describe Command behaviour and the interface.
 */
public abstract class Command {
    private boolean isExit;

    /**
     * Constructor for a default command.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Sets the exit status of the command to true.
     */
    public void exit() {
        this.isExit = true;
    }

    /**
     * Gets the exit status of the Command.
     *
     * @return the exit status of the Command.
     */
    public boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Executes the Command
     *
     * @param taskList the TaskList used to execute the Command.
     * @param terminal the Terminal used to execute the Command.
     * @throws CommandException if the command fails.
     */
    public abstract void execute(TaskList taskList, Terminal terminal) throws CommandException;

    public void save(SaveData saveData) {
        SaveHandler.save(saveData);
    }

}
