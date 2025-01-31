import java.lang.StringBuilder;

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
}
