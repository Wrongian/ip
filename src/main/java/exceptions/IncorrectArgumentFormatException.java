package exceptions;

public class IncorrectArgumentFormatException extends DukeException{

    public IncorrectArgumentFormatException() {
        super("Incorrect Argument Format");
    }

    public IncorrectArgumentFormatException(String msg) {
        super("Incorrect Argument Format: \n" + msg);
    }
}
