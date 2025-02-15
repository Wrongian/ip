package demacia.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import demacia.exceptions.FileNotReadableException;
import demacia.exceptions.FileNotWritableException;



/**
 * Class to methods relating to handling files.
 */
public class FileHandler {
    /**
     * Read a file with the specified file path.
     *
     * @param filePath The file path as a string.
     * @return The read file as String.
     * @throws FileNotFoundException If the file cannot be located at the given path.
     * @throws FileNotReadableException If the file cannot be read.
     */
    public static String readFile(
            String filePath) throws FileNotFoundException, FileNotReadableException, SecurityException {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found");
        }
        if (!file.canRead()) {
            throw new FileNotReadableException(filePath);
        }

        assert(file.isFile());

        // read the file
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        scanner.close();

        return stringBuilder.toString();
    }

    /**
     * Write a String to a file with a designated file path.
     *
     * @param filePath The path to write to as a String.
     * @param toWrite The string to write.
     * @throws FileNotWritableException If the file is not writable.
     * @throws IOException Indicates failed or interrupted I/O operation(s).
     */
    public static void writeFile(
            String filePath, String toWrite) throws FileNotWritableException, IOException, SecurityException {
        File file = new File(filePath);

        // create file if doesnt exist
        if (!file.exists()) {
            file.createNewFile();
        }
        // check if writable
        if (!file.canWrite()) {
            throw new FileNotWritableException(filePath);
        }

        assert(file.isFile());

        // write to the file
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(toWrite);
        fileWriter.close();

    }

    /**
     * Checks if the directory exists and creates it if it doesnt exist.
     *
     * @param dirPath The path of the directory to check/create.
     */
    public static void createDirIfNotExists(String dirPath) throws SecurityException {
        File directory = new File(dirPath);

        if (!directory.exists()) {
            directory.mkdir();
        }

    }

}
