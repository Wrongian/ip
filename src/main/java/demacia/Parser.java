package demacia;

import java.util.HashMap;

import demacia.commands.ByeCommand;
import demacia.commands.Command;
import demacia.commands.DeadlineCommand;
import demacia.commands.DeleteCommand;
import demacia.commands.EventCommand;
import demacia.commands.FindCommand;
import demacia.commands.GetNoteCommand;
import demacia.commands.ListCommand;
import demacia.commands.MarkCommand;
import demacia.commands.SetNoteCommand;
import demacia.commands.TodoCommand;
import demacia.commands.UnmarkCommand;
import demacia.exceptions.IncorrectArgumentFormatException;

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

        HashMap<String, String> cmds = Parser.getCmds(args);

        // build base args
        String[] baseArgs = args[0].split(" ");

        String firstArg = Parser.getFirstArg(baseArgs);

        String cmd = baseArgs[0];

        switch (cmd) {
        case "bye":
            return ByeCommand.makeCommand(firstArg, args, cmds);
        case "list":
            return ListCommand.makeCommand(firstArg, args, cmds);
        case "mark":
            return MarkCommand.makeCommand(firstArg, args, cmds);
        case "unmark":
            return UnmarkCommand.makeCommand(firstArg, args, cmds);
        case "delete":
            return DeleteCommand.makeCommand(firstArg, args, cmds);
        case "todo":
            return TodoCommand.makeCommand(firstArg, args, cmds);
        case "deadline":
            return DeadlineCommand.makeCommand(firstArg, args, cmds);
        case "event":
            return EventCommand.makeCommand(firstArg, args, cmds);
        case "find":
            return FindCommand.makeCommand(firstArg, args, cmds);
        case "getnote":
            return GetNoteCommand.makeCommand(firstArg, args, cmds);
        case "setnote":
            return SetNoteCommand.makeCommand(firstArg, args, cmds);
        default:
            throw new IncorrectArgumentFormatException("Command does not exist");
        }
    }

    /**
     * Get the arguments of a command from the raw split String array of arguments.
     *
     * @param args The arguments of the command.
     * @return Return the arguments of the command as a HashMap.
     */
    private static HashMap<String, String> getCmds(String[] args) {
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

        return cmds;
    }

    /**
     * Gets the first argument of the command from the raw String
     * of the first part of the arguments of the command.
     *
     * @param baseArgs The first part of the arguments of the command as a String Array.
     * @return The first argument of the command as a String.
     */
    private static String getFirstArg(String[] baseArgs) {
        String firstArg = "";
        if (baseArgs.length > 1) {
            StringBuilder nameBuilder = new StringBuilder();

            for (int i = 1; i < baseArgs.length; i++) {
                nameBuilder.append(baseArgs[i]);
                nameBuilder.append(" ");
            }

            if (!nameBuilder.isEmpty()) {
                nameBuilder.deleteCharAt(nameBuilder.length() - 1);
            }

            firstArg = nameBuilder.toString();
        }
        return firstArg;
    }
}
