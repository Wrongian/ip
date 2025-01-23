import java.lang.StringBuilder;

public class Utils {
    public static String joinStringArray(String[] arr, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end + 1; i++) {
           sb.append(arr[i]);
           sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String joinStringArray(String[] arr, int start) {
        return joinStringArray(arr, start, arr.length - 1);
    }

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
