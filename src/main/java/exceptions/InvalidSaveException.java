package exceptions;

public class InvalidSaveException extends Exception{

    public InvalidSaveException(String msg) {
        super("Invalid save" + "\n" + msg);
    }
}
