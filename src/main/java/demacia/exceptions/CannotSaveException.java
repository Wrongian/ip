package demacia.exceptions;

/**
 * Class for exception relating to not being able to save.
 */
public class CannotSaveException extends DukeException {

    /**
     * Constructor for the CannotSaveException.
     */
    public CannotSaveException() {
        super("Task list cannot be automatically saved");
    }
}
