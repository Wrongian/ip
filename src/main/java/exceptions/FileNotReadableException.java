package exceptions;

public class FileNotReadableException extends DukeException {

    public FileNotReadableException(String filePath) {
        super("File at path " + filePath + " is not readable\nPlease enable the file to be readable");
    }
}
