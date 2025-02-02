package demacia.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

/**
 * Class to test Utils class.
 */
public class UtilsTest {

    @Test
    public void stringIsIndex_normalInputs_correct() {
        assert(Utils.stringIsIndex("1000"));

        assert(Utils.stringIsIndex("1450"));

        assert(Utils.stringIsIndex("60000"));

        assert(Utils.stringIsIndex("3543"));
    }

    @Test
    public void stringIsIndex_emptyInput_correct() {
        assert(!Utils.stringIsIndex(""));
    }

    @Test
    public void stringIsIndex_negativeNumbers_correct() {
        assert(!Utils.stringIsIndex("-140"));

        assert(!Utils.stringIsIndex("-1"));

        assert(!Utils.stringIsIndex("-10"));

        assert(!Utils.stringIsIndex("-100000"));

        assert(!Utils.stringIsIndex("-154361"));

    }

    @Test
    public void stringIsIndex_zeroInput_correct() {
        assert(Utils.stringIsIndex("0"));
    }

    @Test
    public void stringIsIndex_decimals_correct() {
        assert(!Utils.stringIsIndex("0.0"));

        assert(!Utils.stringIsIndex("0.1"));

        assert(!Utils.stringIsIndex("-0.1"));
    }

    @Test
    public void formatDateTime_normalInputs_correct() {
        LocalDateTime dateTimeStr = LocalDateTime.of(2002, 1, 22, 6, 0);

        String expected = "2002-01-22 06-00";
        String actual = Utils.formatDateTime(dateTimeStr);
        assert(expected.equals(actual));
    }

    @Test
    public void parseDateTime_normalInput_correct() {
        String input = "2002-01-22 06-00";

        LocalDateTime expected = LocalDateTime.of(2002, 1, 22, 6, 0);
        LocalDateTime actual = Utils.parseDateTime(input);
        assert(expected.equals(actual));
    }

    @Test
    public void parseDateTime_wrongFormat_exception() {
        String inputOne = "2002-01-22 06/00";

        assertThrows(DateTimeParseException.class, () -> Utils.parseDateTime(inputOne));

        String inputTwo = "200-01-22 06-00";
        assertThrows(DateTimeParseException.class, () -> Utils.parseDateTime(inputTwo));

        String inputThree = "20002-01-22 06-00";
        assertThrows(DateTimeParseException.class, () -> Utils.parseDateTime(inputThree));

        String inputFour = "2002-01-22 0600";
        assertThrows(DateTimeParseException.class, () -> Utils.parseDateTime(inputFour));

        String inputFive = "2002-01-22 06";
        assertThrows(DateTimeParseException.class, () -> Utils.parseDateTime(inputFive));

        String inputSix = "2002-01-22";
        assertThrows(DateTimeParseException.class, () -> Utils.parseDateTime(inputSix));

        String inputSeven = "2002-01-2206-00";
        assertThrows(DateTimeParseException.class, () -> Utils.parseDateTime(inputSeven));

        String inputEight = "2002-01-22 06-00 ";
        assertThrows(DateTimeParseException.class, () -> Utils.parseDateTime(inputEight));

        String inputNine = "2002-01-22T06-00";
        assertThrows(DateTimeParseException.class, () -> Utils.parseDateTime(inputNine));

    }

}
