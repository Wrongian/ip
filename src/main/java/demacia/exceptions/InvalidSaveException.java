package demacia.exceptions;

/**
 * Class for the exception relating to having a save file with the wrong format.
 */
public class InvalidSaveException extends DukeException {

    /**
     * Constructor to create an InvalidSaveException.
     *
     * @param msg The custom message to give to the exception,
     */
    public InvalidSaveException(String msg) {
        super("Invalid save" + "\n" + msg);
    }
}
