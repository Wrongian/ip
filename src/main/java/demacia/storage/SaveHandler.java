package demacia.storage;

import demacia.exceptions.CannotSaveException;
import demacia.exceptions.InvalidSaveException;
import demacia.tasks.TaskList;

/**
 * Class to handle saving data to files.
 */
public class SaveHandler {

    /*
        Format to save:
        new line means new task.
        , means new key value pair
        Key value pair format is <key>:<value>
    */

    // hard coded save paths
    private static final String SAVE_PATH = "./data/save.txt";
    private static final String DIR_PATH = "./data";


    /**
     * Saves the SaveData as a file with the default save file path.
     *
     * @param saveData The SaveData to save into a file.
     */
    public static void save(SaveData saveData) throws CannotSaveException {
        String saveString = saveData.save();

        FileHandler.createDirIfNotExists(SaveHandler.DIR_PATH);
        try {
            FileHandler.writeFile(SaveHandler.SAVE_PATH, saveString);
        } catch (Exception e) {
            throw new CannotSaveException();
        }
    }

    /**
     * Load the SavaData from the default save file path and returns it.
     *
     * @return The SaveData from the default save file path.
     */
    public static SaveData load() throws InvalidSaveException {
        try {
            String data = FileHandler.readFile(SaveHandler.SAVE_PATH);
            return new SaveData(data);
        } catch (InvalidSaveException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidSaveException("");
        }
    }

}
