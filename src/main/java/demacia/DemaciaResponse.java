package demacia;

/**
 * Class representing a response from the chatbot.
 */
public class DemaciaResponse {
    private final String response;
    private final boolean isExit;

    /**
     * Constructor to create a DemaciaResponse object contain a response
     * for the chatbot.
     *
     * @param response The response from the chatbot as a String.
     * @param isExit THe boolean value for whether to exit out.
     */
    public DemaciaResponse(String response, boolean isExit) {
        this.response = response;
        this.isExit = isExit;
    }

    /**
     * The getter for the response String.
     *
     * @return The response String.
     */
    public String getResponse() {
        return this.response;
    }

    /**
     * The getter for the isExit variable.
     * @return The boolean isExit for whether the bot exits.
     */
    public boolean getIsExit() {
        return this.isExit;
    }
}
