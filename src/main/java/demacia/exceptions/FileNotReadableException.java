package demacia.exceptions;

public class FileNotReadableException extends DukeException {

    public FileNotReadableException(String filePath) {
        super("File at path " + filePath + " is not readable\nPlease enable the save file" +
                " to be readable by changing its permissions");
    }
}
