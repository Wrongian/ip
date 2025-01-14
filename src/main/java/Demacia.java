import java.lang.StringBuilder;
public class Demacia {

    private static final int MAX_TASKS = 100;

    private final Terminal terminal;
    private String[] tasks;
    private int taskIndex;

    public Demacia() {
        this.terminal = new Terminal();
        this.tasks = new String[Demacia.MAX_TASKS];
        this.taskIndex = 0;
    }

    public void addTask(String task) {
        // modify task array
        this.tasks[taskIndex] = task;
        this.taskIndex += 1;

        // msg
        this.terminal.printHorizontal();
        String msg = "added: " + task;
        this.terminal.output(msg);
        this.terminal.printHorizontal();
    }

    public void listTasks() {
        this.terminal.printHorizontal();
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < this.taskIndex; i++) {
            msg.append(String.valueOf(i + 1));
            msg.append(". ");
            msg.append(this.tasks[i]);
            msg.append("\n");
        }
        // delete last newline
        msg.deleteCharAt(msg.length() - 1);
        this.terminal.output(msg.toString());
        this.terminal.printHorizontal();
    }

    public void greet() {
        this.terminal.printHorizontal();
        this.terminal.output("Hello I am Demacia, a chatbot");
        this.terminal.output("Type what you desire");
        this.terminal.printHorizontal();
    }

    public void start() {
        // get messages from user
        while (true) {
            String msg = this.terminal.input();
            switch (msg) {
                case "bye":
                    this.exit();
                    break;
                case "list":
                    this.listTasks();
                    break;
                default:
                    addTask(msg);
                    break;
            }
        }

    }

    public void exit() {
        this.terminal.printHorizontal();
        this.terminal.output("Buybye, see ya later...");
        this.terminal.printHorizontal();
        System.exit(0);
    }

    public static void main(String[] args) {
        Demacia bot = new Demacia();
        bot.greet();
        bot.start();
    }
}
