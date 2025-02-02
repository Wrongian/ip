package demacia.storage;

/**
 * Interface to indicate if a class is Saveable.
 */
public interface Saveable {

    /**
     * Saves the object as a String.
     *
     * @return The serialised object as a String.
     */
    public String save();

}
