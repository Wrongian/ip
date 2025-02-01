package demacia.utils;
import java.lang.StringBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {
    public static boolean stringIsInt(String str) {
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

    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }

    public static String formatDateTime(LocalDateTime dateTime) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
        return dateTimeFormatter.format(dateTime);
    }

    public static String showDateTime(LocalDateTime dateTime) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTimeFormatter.format(dateTime);
    }
}
