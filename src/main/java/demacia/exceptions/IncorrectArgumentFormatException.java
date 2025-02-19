package demacia.exceptions;

/**
 * Class for the exception relating to having the wrong format for the arguments for
 * the command such that the chatbot cannot parse it.
 */
public class IncorrectArgumentFormatException extends DemaciaException {

    /**
     * Constructor for the IncorrectArgumentFormatException.
     * By default, it will have the message "Incorrect Argument Format".
     */
    public IncorrectArgumentFormatException() {
        super("Incorrect Argument Format");
    }

    /**
     * Constructor for the IncorrectArgumentFormatException.
     *
     * @param msg The specified message for the exception.
     */
    public IncorrectArgumentFormatException(String msg) {
        super("Incorrect Argument Format: \n" + msg);
    }
}
