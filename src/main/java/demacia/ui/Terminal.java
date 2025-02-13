package demacia.ui;

import java.util.Scanner;

/**
 * Class to represent the terminal UI of the chatbot.
 */
public class Terminal {

    private final Scanner scanner;
    private StringBuilder stringBuffer;

    /**
     * Constructor to create a Terminal.
     */
    public Terminal() {
        this.scanner = new Scanner(System.in);
        this.stringBuffer = new StringBuilder();
    }

    /**
     * Gets the next line from the standard input.
     *
     * @return The line from the standard input as a String.
     */
    public String input() {
        return this.scanner.nextLine();
    }

    /**
     * Adds the input to the buffer.
     *
     * @param newOutput The input to append to the buffer.
     */
    public void buffer(String newOutput) {
        this.stringBuffer.append(newOutput);
        this.stringBuffer.append("\n");
    }

    /**
     * Flushes the buffer.
     *
     * @return The flushed buffer as a String.
     */
    public String getOutput() {

        String outputString = this.stringBuffer.toString();
        this.clear();
        assert(this.stringBuffer.isEmpty());
        return outputString;
    }

    /**
     * Clear the buffer.
     */
    private void clear() {
        this.stringBuffer = new StringBuilder();
    }

}
