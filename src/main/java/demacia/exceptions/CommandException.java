package demacia.exceptions;

/**
 * Class for exceptions relating to commands failing.
 */
public class CommandException extends DukeException {

    /**
     * Constructor for the exception.
     *
     * @param msg The default error message for the exception.
     */
    public CommandException(String msg) {
        super("There is an error executing the command\n" + msg);
    }
}
