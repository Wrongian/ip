package exceptions;

public class FileNotWritableException extends DukeException {

    public FileNotWritableException(String filePath) {
        super("File at path " + filePath + " is not writable\nPlease enable the save file" +
                " to be writable by changing its permissions");
    }
}
