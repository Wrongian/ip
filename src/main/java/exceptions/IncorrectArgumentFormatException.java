package exceptions;

public class IncorrectArgumentFormatException extends DukeException{

    public IncorrectArgumentFormatException() {
        this("Incorrect Argument Format");
    }

    public IncorrectArgumentFormatException(String msg) {
        super(msg);
    }
}
