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

    public void markTask(int index) {
        // get task
        this.terminal.printHorizontal();
        this.taskList.markTask(index);
        this.terminal.output("Marked this task as done: ");
        this.terminal.output(this.taskList.getTaskString(index));
        this.terminal.printHorizontal();
    }

    public void unmarkTask(int index) {
        // get task
        this.terminal.printHorizontal();
        this.taskList.unmarkTask(index);
        this.terminal.output("Marked this task as not done yet: ");
        this.terminal.output(this.taskList.getTaskString(index));
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
        // todo: enums for the commands
        // todo: higher order function so that dont have to add horizontal lines to everything
        while (true) {
            String msg = this.terminal.input();
            String[] cmds = msg.split(" ");
//            this.terminal.output(cmds[0]);
            // todo: checking and sanitize commands array
            switch (cmds[0]) {
                case "bye":
                    this.exit();
                    break;
                case "list":
                    this.listTasks();
                    break;
                case "mark":
                    // todo: check if integer and check correct number of arguments
                    this.markTask(Integer.parseInt(cmds[1]) - 1);
                    break;
                case "unmark":
                    // todo: check if integer and check correct number of arguments
                    this.unmarkTask(Integer.parseInt(cmds[1]) - 1);
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
