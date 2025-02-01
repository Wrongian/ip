package demacia.exceptions;

public class InvalidSaveException extends DukeException {

    public InvalidSaveException(String msg) {
        super("Invalid save" + "\n" + msg);
    }
}
