package demacia.ui;

import java.util.Scanner;

/**
 * Class to represent the terminal UI of the chatbot.
 */
public class Terminal {

    private final Scanner scanner;

    /**
     * Constructor to create a Terminal.
     */
    public Terminal() {
       this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a horizontal line in standard output.
     */
    public void printHorizontal() {
        System.out.println("------------------------------");
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
     * Prints a String to the standard output.
     *
     * @param msg The String to print to the standard output.
     */
    public void output(String msg) {
        System.out.println(msg);
    }

}
