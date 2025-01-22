public class Demacia {
    private final Terminal terminal;
    private final TaskList taskList;

    public Demacia() {
        this.terminal = new Terminal();
        this.taskList = new TaskList();
   }

    private void addTodo(String task) {
        this.terminal.printHorizontal();
        int index = taskList.addTodo(task);
        String msg = "Got it. I have added this task:\n" +
                taskList.getTaskString(index);
        this.terminal.output(msg);
        this.terminal.output("Now you have " + String.valueOf(index + 1) + " tasks in the list");
        this.terminal.printHorizontal();
    }

    private void addDeadline(String task, String by) {
        this.terminal.printHorizontal();
        int index = taskList.addDeadline(task, by);
        String msg = "Got it. I have added this task:\n" +
                taskList.getTaskString(index);
        this.terminal.output(msg);
        this.terminal.output("Now you have " + String.valueOf(index + 1) + " tasks in the list");
        this.terminal.printHorizontal();
    }

    private void addEvent(String task, String from, String to) {
        this.terminal.printHorizontal();
        int index = taskList.addEvent(task, from, to);
        String msg = "Got it. I have added this task:\n" +
                taskList.getTaskString(index);
        this.terminal.output(msg);
        this.terminal.output("Now you have " + String.valueOf(index + 1) + " tasks in the list");
        this.terminal.printHorizontal();
    }

    public void listTasks() {
        this.terminal.printHorizontal();
        String msg = this.taskList.listTasks();
        this.terminal.output(msg);
        this.terminal.printHorizontal();
    }

    private void markTask(int index) {
        // get task
        this.terminal.printHorizontal();
        this.taskList.markTask(index);
        this.terminal.output("Marked this task as done:");
        this.terminal.output(this.taskList.getTaskString(index));
        this.terminal.printHorizontal();
    }

    private void unmarkTask(int index) {
        // get task
        this.terminal.printHorizontal();
        this.taskList.unmarkTask(index);
        this.terminal.output("Marked this task as not done yet:");
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
            String name;
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
            case "todo":
                // todo: check arguments
                this.addTodo(Utils.joinStringArray(cmds,1));
                break;
            case "deadline":
                // todo: check arguments
                // find "by"
                int by_index = 0;
                for (int i = 1; i < cmds.length; i++) {
                    if (cmds[i].equals("/by")) {
                        by_index = i;
                        break;
                    }
                }
                name = Utils.joinStringArray(cmds,1, by_index - 1);
                String by = Utils.joinStringArray(cmds, by_index + 1);
                this.addDeadline(name, by);
                break;
            case "event":
                // todo: check arguments
                // find "from"
                int from_index = 0;
                for (int i = 1; i < cmds.length; i++) {
                    if (cmds[i].equals("/from")) {
                        from_index = i;
                        break;
                    }
                }
                // find to
                int to_index = 0;
                for (int i = 1; i < cmds.length; i++) {
                    if (cmds[i].equals("/to")) {
                        to_index = i;
                        break;
                    }
                }
                name = Utils.joinStringArray(cmds,1, from_index - 1);
                String from = Utils.joinStringArray(cmds,from_index + 1, to_index - 1);
                String to = Utils.joinStringArray(cmds, to_index + 1);
                this.addEvent(name, from, to);
                break;
            default:
                break;
            }
        }
    }

    private void exit() {
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
