package demacia.exceptions;

/**
 * Class for exception relating to the file not having read permissions.
 */
public class FileNotReadableException extends DukeException {

    /**
     * Constructor for the FileNotReadableException.
     *
     * @param filePath The path to the file that is not readable(causing the error).
     */
    public FileNotReadableException(String filePath) {
        super("File at path " + filePath + " is not readable\nPlease enable the save file" +
                " to be readable by changing its permissions");
    }
}
