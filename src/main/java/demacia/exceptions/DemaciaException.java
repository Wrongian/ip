package demacia.exceptions;

/**
 * Class for general exception relating to the chatbot.
 * Other exception classes are supposed to subclass this.
 */
public class DemaciaException extends Exception {

    /**
     * Constructor for the exception.
     *
     * @param msg The default error message for the exception.
     */
    public DemaciaException(String msg) {
        super(msg);
    }
}
