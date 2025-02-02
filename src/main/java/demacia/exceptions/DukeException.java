package demacia.exceptions;

/**
 * Class for general exception relating to the chatbot.
 * Other exception classes are supposed to subclass this.
 */
public class DukeException extends Exception {

    /**
     * Constructor for the exception.
     *
     * @param msg The default error message for the exception.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
