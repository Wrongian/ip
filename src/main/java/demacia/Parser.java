package demacia;

import demacia.commands.*;
import demacia.exceptions.IncorrectArgumentFormatException;
import demacia.utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class Parser {
    public static Command parseCommand(String msg) throws IncorrectArgumentFormatException {
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
        switch (cmd) {
            case "bye":
                if (!first_arg.isEmpty() || args.length > 1) {
                    throw new IncorrectArgumentFormatException(
                            "Usage: \nbye");
                }
                return new ByeCommand();
            case "list":
                if (!first_arg.isEmpty() || args.length > 1) {
                    throw new IncorrectArgumentFormatException("Usage: \nlist");
                }
                return new ListCommand();
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

                return new MarkCommand(Integer.parseInt(first_arg) - 1);
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
                return new UnmarkCommand(Integer.parseInt(first_arg) - 1);
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

                return new DeleteCommand(Integer.parseInt(first_arg) - 1);
            case "todo":
                if (first_arg.isEmpty() || args.length > 1) {
                    throw new IncorrectArgumentFormatException(
                            "Usage: \ntodo <task name>");
                }
                return new TodoCommand(first_arg);
            case "deadline":
                if (first_arg.isEmpty() || args.length != 2 || !cmds.containsKey("by")) {
                    throw new IncorrectArgumentFormatException(
                            "Usage: \ntodo <task name> /by <deadline>");
                }
                try {
                    LocalDateTime byDateTime = Utils.parseDateTime(cmds.get("by"));
                    return new DeadlineCommand(first_arg, byDateTime);
                } catch (DateTimeParseException e) {
                    throw new IncorrectArgumentFormatException("Date/time format error\nFormat should be: yyyy-MM-dd HH-mm\n" +
                            "yyyy is year, MM is the month, dd is the day\n" +
                            "HH is the hour and mm are the minutes");
                }
            case "event":
                if (first_arg.isEmpty() || args.length != 3 ||
                        !cmds.containsKey("from") || !cmds.containsKey("to")) {
                    throw new IncorrectArgumentFormatException(
                            "Usage: \ntodo <task name> /from <from> /to <to>");
                }
                try {
                    LocalDateTime fromDateTime = Utils.parseDateTime(cmds.get("from"));
                    LocalDateTime toDateTime = Utils.parseDateTime(cmds.get("to"));
                    return new EventCommand(first_arg, fromDateTime, toDateTime);

                } catch (DateTimeParseException e) {
                    throw new IncorrectArgumentFormatException("Date/time format error\nFormat should be: yyyy-MM-dd HH-mm\n" +
                            "yyyy is year, MM is the month, dd is the day\n" +
                            "HH is the hour and mm are the minutes");
                }

            default:
                throw new IncorrectArgumentFormatException("Command does not exist");
        }
    }
}
