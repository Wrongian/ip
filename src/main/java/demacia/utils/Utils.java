package demacia.utils;
import java.lang.StringBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class to contain various utility methods.
 */
public class Utils {

    /**
     * Checks if the String is an index of an array.
     *
     * @param str The String to check.
     * @return Boolean to show if the String is an index of an array.
     */
    public static boolean stringIsIndex(String str) {
        // only checks if positive integer
        // empty string
        if (str.isEmpty()) {
            return false;
        }

        // check if every character is a digit
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
               return false;
            }
        }
        return true;
    }

    /**
     * Parses a String representation of the date and time.
     * The format is yyyy-MM-dd HH-mm.
     * yyyy is the year, MM is the month, dd is the day, HH is the hour and mm is the minutes.
     *
     * @param dateTime The String to parse.
     * @return The LocalDateTime representation of the String.
     * @throws DateTimeParseException If the String cannot be parsed.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }

    /**
     * Formats the LocalDateTime object into a String
     * The format is yyyy-MM-dd HH-mm.
     * yyyy is the year, MM is the month, dd is the day, HH is the hour and mm is the minutes.
     *
     * @param dateTime The LocalDateTime object to format.
     * @return The formatted String.
     * @throws DateTimeParseException If the LocalDateTime object cannot be formatted.
     */
    public static String formatDateTime(LocalDateTime dateTime) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
        return dateTimeFormatter.format(dateTime);
    }

    /**
     * Gives a String representation of the LocalDateTime object.
     * The format is yyyy-MM-dd HH:mm.
     * yyyy is the year, MM is the month, dd is the day, HH is the hour and mm is the minutes.
     *
     * @param dateTime The LocalDateTime object to turn into a String.
     * @return The specific String representation of the LocalDateTime object.
     * @throws DateTimeParseException If the LocalDateTime object cannot be parsed into a String.
     */
    public static String showDateTime(LocalDateTime dateTime) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTimeFormatter.format(dateTime);
    }
}
