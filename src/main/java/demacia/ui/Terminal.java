package demacia.ui;

import java.util.Scanner;

public class Terminal {

    private final Scanner scanner;

    public Terminal() {
        this.scanner = new Scanner(System.in);
    }

    public void printHorizontal() {
        System.out.println("------------------------------");
    }

    public String input() {
        return this.scanner.nextLine();
    }

    public void output(String msg) {
        System.out.println(msg);
    }

}
