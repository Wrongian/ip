import java.util.HashMap;

public class Demacia {
    private final Terminal terminal;
    private final TaskList taskList;

    public Demacia() {
        this.terminal = new Terminal();
        this.taskList = new TaskList();
   }

    private void addTodo(String task) {
        this.terminal.printHorizontal();
        try {
            int index = taskList.addTodo(task);
            String msg = "Got it. I have added this task:\n" +
                    taskList.getTaskString(index);
            this.terminal.output(msg);
            this.terminal.output("Now you have " + String.valueOf(index + 1) + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            terminal.output(e.getMessage());
        }
        this.terminal.printHorizontal();
    }

    private void addDeadline(String task, String by) {
        this.terminal.printHorizontal();
        try {
            int index = taskList.addDeadline(task, by);
            String msg = "Got it. I have added this task:\n" +
                    taskList.getTaskString(index);
            this.terminal.output(msg);
            this.terminal.output("Now you have " + String.valueOf(index + 1) + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            terminal.output(e.getMessage());
        }
        this.terminal.printHorizontal();
    }

    private void addEvent(String task, String from, String to) {
        this.terminal.printHorizontal();
        try {
            int index = taskList.addEvent(task, from, to);
            String msg = "Got it. I have added this task:\n" +
                    taskList.getTaskString(index);
            this.terminal.output(msg);
            this.terminal.output("Now you have " + String.valueOf(index + 1) + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            terminal.output(e.getMessage());
        }
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
        try {
            this.taskList.markTask(index);
            this.terminal.output("Marked this task as done:");
            this.terminal.output(this.taskList.getTaskString(index));
        } catch (IndexOutOfBoundsException e) {
            this.terminal.output(e.getMessage());
        }
        this.terminal.printHorizontal();
    }

    private void unmarkTask(int index) {
        // get task
        this.terminal.printHorizontal();
        try {
            this.taskList.unmarkTask(index);
            this.terminal.output("Marked this task as not done yet:");
            this.terminal.output(this.taskList.getTaskString(index));
        } catch (IndexOutOfBoundsException e) {
            this.terminal.output(e.getMessage());
        }
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

            // hashmap rest of the arguments
            String[] args = msg.split(" /");
            HashMap<String, String> cmds = new HashMap<String, String>();
            if (args.length > 1) {
                for (int i = 1; i < args.length; i++) {
                    String[] argArr = args[i].split(" ");
                    StringBuilder argsBuilder = new StringBuilder();
                    for (int j = 1; j < argArr.length; j++) {
                        argsBuilder.append(argArr[j]);
                        argsBuilder.append(" ");
                    }
                    if (!argsBuilder.isEmpty()) {
                       argsBuilder.deleteCharAt(argsBuilder.length() - 1);
                    }
                    cmds.put(argArr[0], argsBuilder.toString());
                }
            }

            // build base args
            String[] base_cmd = args[0].split(" ");
            String first_arg = "";
            String cmd = "";
            if (base_cmd.length == 1) {
                cmd = base_cmd[0];
            } else if (base_cmd.length > 1) {
                StringBuilder nameBuilder = new StringBuilder();
                for (int i = 1; i < base_cmd.length; i++) {
                    nameBuilder.append(base_cmd[i]);
                    nameBuilder.append(" ");
                }
                if (!nameBuilder.isEmpty()) {
                    nameBuilder.deleteCharAt(nameBuilder.length() - 1);
                }
                first_arg = nameBuilder.toString();
                cmd = base_cmd[0];
            }
            // todo: checking and sanitize commands array
            switch (cmd) {
            case "bye":
                this.exit();
                break;
            case "list":
                this.listTasks();
                break;
            case "mark":
                // todo: check if integer and check correct number of arguments
                this.markTask(Integer.parseInt(first_arg) - 1);
                break;
            case "unmark":
                // todo: check if integer and check correct number of arguments
                this.unmarkTask(Integer.parseInt(first_arg) - 1);
                break;
            case "todo":
                // todo: check arguments
                this.addTodo(first_arg);
                break;
            case "deadline":
                // todo: check arguments
                this.addDeadline(first_arg, cmds.get("by"));
                break;
            case "event":
                // todo: check arguments
                this.addEvent(first_arg, cmds.get("from"), cmds.get("to"));
                break;
            default:
                // todo: invalid command throw error
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
