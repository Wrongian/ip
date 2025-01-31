import exceptions.IncorrectArgumentFormatException;

import java.util.HashMap;

public class Demacia {
    private final Terminal terminal;
    private TaskList taskList;

    public Demacia() {
        this.terminal = new Terminal();
        this.taskList = new TaskList();

        SaveData saveData = SaveHandler.load();
        this.taskList = saveData.getTaskList();
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
        this.save();
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
        this.save();
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
        this.save();
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
        this.save();
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
        this.save();
        this.terminal.printHorizontal();
    }

    private void deleteTask(int index) {
        // get task
        this.terminal.printHorizontal();
        try {
            String taskString = this.taskList.getTaskString(index);
            this.taskList.deleteTask(index);
            this.terminal.output("I have removed the task");
            this.terminal.output(taskString);
        } catch (IndexOutOfBoundsException e) {
            this.terminal.output(e.getMessage());
        }
        this.save();
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
            try {
                switch (cmd) {
                    case "bye":
                        if (!first_arg.isEmpty() || args.length > 1) {
                            throw new IncorrectArgumentFormatException(
                                    "Usage: \nbye");
                        }
                        this.exit();
                        break;
                    case "list":
                        if (!first_arg.isEmpty() || args.length > 1) {
                            throw new IncorrectArgumentFormatException("Usage: \nlist");
                        }
                        this.listTasks();
                        break;
                    case "mark":
                        if (first_arg.isEmpty() || args.length > 1) {
                            throw new IncorrectArgumentFormatException(
                                    "Usage: \nmark <task number>");
                        }
                        // check if int
                        if (!Utils.stringIsInt(first_arg)) {
                            throw new IncorrectArgumentFormatException(
                                    "Usage: \nmark <task number>");
                        }

                        this.markTask(Integer.parseInt(first_arg) - 1);
                        break;
                    case "unmark":
                        if (first_arg.isEmpty() || args.length > 1) {
                            throw new IncorrectArgumentFormatException(
                                    "Usage: \nunmark <task number>");
                        }
                        // check if int
                        if (!Utils.stringIsInt(first_arg)) {
                            throw new IncorrectArgumentFormatException(
                                    "Usage: \nmark <task number>");
                        }
                        this.unmarkTask(Integer.parseInt(first_arg) - 1);
                        break;
                    case "delete":
                        if (first_arg.isEmpty() || args.length > 1) {
                            throw new IncorrectArgumentFormatException(
                                    "Usage: \ndelete <task number>");
                        }
                        // check if int
                        if (!Utils.stringIsInt(first_arg)) {
                            throw new IncorrectArgumentFormatException(
                                    "Usage: \nmark <task number>");
                        }

                        this.deleteTask(Integer.parseInt(first_arg) - 1);
                        break;
                    case "todo":
                        if (first_arg.isEmpty() || args.length > 1) {
                            throw new IncorrectArgumentFormatException(
                                    "Usage: \ntodo <task name>");
                        }
                        this.addTodo(first_arg);
                        break;
                    case "deadline":
                        if (first_arg.isEmpty() || args.length != 2 || !cmds.containsKey("by")) {
                            throw new IncorrectArgumentFormatException(
                                    "Usage: \ntodo <task name> /by <deadline>");
                        }
                        this.addDeadline(first_arg, cmds.get("by"));
                        break;
                    case "event":
                        if (first_arg.isEmpty() || args.length != 3 ||
                                !cmds.containsKey("from") || !cmds.containsKey("to")) {
                            throw new IncorrectArgumentFormatException(
                                    "Usage: \ntodo <task name> /from <from> /to <to>");
                        }
                        this.addEvent(first_arg, cmds.get("from"), cmds.get("to"));
                        break;
                    default:
                        throw new IncorrectArgumentFormatException("Command does not exist");
                }
            } catch (IncorrectArgumentFormatException e) {
                terminal.output(e.getMessage());
            }
        }
    }


    private void exit() {
        this.terminal.printHorizontal();
        this.terminal.output("Buybye, see ya later...");
        this.terminal.printHorizontal();
        System.exit(0);
    }

    private void save() {
        SaveHandler.save(new SaveData(this.taskList));
    }

    public static void main(String[] args) {
        Demacia bot = new Demacia();
        bot.greet();
        bot.start();
    }
}
