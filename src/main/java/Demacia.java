public class Demacia {
    private final Terminal terminal;
    private final TaskList taskList;

    public Demacia() {
        this.terminal = new Terminal();
        this.taskList = new TaskList();
   }

    public void addTask(String task) {
        // msg
        this.terminal.printHorizontal();
        taskList.addTask(task);
        String msg = "added: " + task;
        this.terminal.output(msg);
        this.terminal.printHorizontal();
    }

    public void listTasks() {
        this.terminal.printHorizontal();
        String msg = this.taskList.listTasks();
        this.terminal.output(msg);
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
