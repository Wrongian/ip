import exceptions.FileNotReadableException;
import exceptions.FileNotWritableException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.StringBuilder;
import java.io.IOException;
import java.io.FileWriter;

public class FileHandler {
    public static String readFile(String filePath) throws FileNotFoundException, FileNotReadableException {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found");
        }
        if (!file.canRead()) {
            throw new FileNotReadableException(filePath);
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        scanner.close();

        return stringBuilder.toString();
    }

    public static void writeFile(String filePath, String toWrite) throws FileNotWritableException, IOException {
        File file = new File(filePath);

        // create file if doesnt exist
        if (!file.exists()) {
            file.createNewFile();
        }
        // check if writable
        if (!file.canWrite()) {
            throw new FileNotWritableException(filePath);
        }

        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(toWrite);
        fileWriter.close();

    }

    public static void createDirIfNotExists(String dirPath) {
       File directory = new File(dirPath);

       // todo: check if securityException is ever thrown
       if (!directory.exists()) {
           directory.mkdir();
       }

    }

}
