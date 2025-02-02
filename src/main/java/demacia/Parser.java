package demacia;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import demacia.commands.ByeCommand;
import demacia.commands.Command;
import demacia.commands.DeadlineCommand;
import demacia.commands.DeleteCommand;
import demacia.commands.EventCommand;
import demacia.commands.FindCommand;
import demacia.commands.ListCommand;
import demacia.commands.MarkCommand;
import demacia.commands.TodoCommand;
import demacia.commands.UnmarkCommand;
import demacia.exceptions.IncorrectArgumentFormatException;
import demacia.utils.Utils;

/**
 * Class to encapsulate the methods to parse commands.
 */
public class Parser {

    /**
     * Parses the String into a Command object.
     *
     * @param msg The String to parse into a Command object.
     * @return The parsed Command object.
     * @throws IncorrectArgumentFormatException If the String cannot be parsed into a Command.
     */
    public static Command parseCommand(String msg) throws IncorrectArgumentFormatException {
        // hashmap rest of the arguments
        String[] args = msg.split(" /");

        HashMap<String, String> cmds = new HashMap<>();

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
        String[] baseArgs = args[0].split(" ");
        String firstArg = "";
        String cmd = "";

        if (baseArgs.length == 1) {
            cmd = baseArgs[0];
        } else if (baseArgs.length > 1) {
            StringBuilder nameBuilder = new StringBuilder();

            for (int i = 1; i < baseArgs.length; i++) {
                nameBuilder.append(baseArgs[i]);
                nameBuilder.append(" ");
            }

            if (!nameBuilder.isEmpty()) {
                nameBuilder.deleteCharAt(nameBuilder.length() - 1);
            }

            firstArg = nameBuilder.toString();
            cmd = baseArgs[0];
        }
        switch (cmd) {
        case "bye":
            if (!firstArg.isEmpty() || args.length > 1) {
                throw new IncorrectArgumentFormatException(
                        "Usage: \nbye");
            }
            return new ByeCommand();
        case "list":
            if (!firstArg.isEmpty() || args.length > 1) {
                throw new IncorrectArgumentFormatException("Usage: \nlist");
            }
            return new ListCommand();
        case "mark":
            if (firstArg.isEmpty() || args.length > 1) {
                throw new IncorrectArgumentFormatException(
                        "Usage: \nmark <task number>");
            }
            // check if int
            if (!Utils.stringIsIndex(firstArg)) {
                throw new IncorrectArgumentFormatException(
                        "Usage: \nmark <task number>");
            }

            return new MarkCommand(Integer.parseInt(firstArg) - 1);
        case "unmark":
            if (firstArg.isEmpty() || args.length > 1) {
                throw new IncorrectArgumentFormatException(
                        "Usage: \nunmark <task number>");
            }
            // check if int
            if (!Utils.stringIsIndex(firstArg)) {
                throw new IncorrectArgumentFormatException(
                        "Usage: \nmark <task number>");
            }
            return new UnmarkCommand(Integer.parseInt(firstArg) - 1);
        case "delete":
            if (firstArg.isEmpty() || args.length > 1) {
                throw new IncorrectArgumentFormatException(
                        "Usage: \ndelete <task number>");
            }
            // check if int
            if (!Utils.stringIsIndex(firstArg)) {
                throw new IncorrectArgumentFormatException(
                        "Usage: \nmark <task number>");
            }

            return new DeleteCommand(Integer.parseInt(firstArg) - 1);
        case "todo":
            if (firstArg.isEmpty() || args.length > 1) {
                throw new IncorrectArgumentFormatException(
                        "Usage: \ntodo <task name>");
            }
            return new TodoCommand(firstArg);
        case "deadline":
            if (firstArg.isEmpty() || args.length != 2 || !cmds.containsKey("by")) {
                throw new IncorrectArgumentFormatException(
                        "Usage: \ntodo <task name> /by <deadline>");
            }
            try {
                LocalDateTime byDateTime = Utils.parseDateTime(cmds.get("by"));
                return new DeadlineCommand(firstArg, byDateTime);
            } catch (DateTimeParseException e) {
                throw new IncorrectArgumentFormatException("Date/time format error\n"
                        + "Format should be: yyyy-MM-dd HH-mm\n"
                        + "yyyy is year,"
                        + " MM is the month, dd is the day\n"
                        + "HH is the hour and mm are the minutes");
            }
        case "event":
            if (firstArg.isEmpty() || args.length != 3
                    || !cmds.containsKey("from") || !cmds.containsKey("to")) {
                throw new IncorrectArgumentFormatException(
                        "Usage: \ntodo <task name> /from <from> /to <to>");
            }
            try {
                LocalDateTime fromDateTime = Utils.parseDateTime(cmds.get("from"));
                LocalDateTime toDateTime = Utils.parseDateTime(cmds.get("to"));
                return new EventCommand(firstArg, fromDateTime, toDateTime);
            } catch (DateTimeParseException e) {
                throw new IncorrectArgumentFormatException("Date/time format error\n"
                        + "Format should be: yyyy-MM-dd HH-mm\n"
                        + "yyyy is year,"
                        + " MM is the month, dd is the day\n"
                        + "HH is the hour and mm are the minutes");
            }
        case "find":
            if (firstArg.isEmpty() || args.length > 1) {
                throw new IncorrectArgumentFormatException(
                        "Usage: \nfind <text to search for>");
            }
            return new FindCommand(firstArg);
        default:
            throw new IncorrectArgumentFormatException("Command does not exist");
        }
    }
}
