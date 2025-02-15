package demacia.storage;

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

    // todo: account for special characters in the strings(maybe instead use \n to split)

    /**
     * Saves the SaveData as a file with the default save file path.
     *
     * @param saveData The SaveData to save into a file.
     */
    public static void save(SaveData saveData) {
        String saveString = saveData.save();

        FileHandler.createDirIfNotExists(SaveHandler.DIR_PATH);
        try {
            FileHandler.writeFile(SaveHandler.SAVE_PATH, saveString);
        } catch (Exception e) {
            System.out.println("Tasks cannot be saved");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Load the SavaData from the default save file path and returns it.
     *
     * @return The SaveData from the default save file path.
     */
    public static SaveData load() {
        try {
            String data = FileHandler.readFile(SaveHandler.SAVE_PATH);

            return new SaveData(data);
        } catch (Exception e) {
            System.out.println("Save cannot be loaded");
            System.out.println(e.getMessage());
            System.out.println("Initialising to default values");

            return new SaveData(new TaskList());
        }
    }

}
