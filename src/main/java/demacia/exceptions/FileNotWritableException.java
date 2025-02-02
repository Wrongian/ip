package demacia.exceptions;

/**
 * Class for exception relating to the file not having write permissions.
 */
public class FileNotWritableException extends DukeException {

    /**
     * Constructor for the FileNotWritableException.
     *
     * @param filePath The path to the file that is not writable(causing the error).
     */
    public FileNotWritableException(String filePath) {
        super("File at path " + filePath + " is not writable\nPlease enable the save file" +
                " to be writable by changing its permissions");
    }
}
