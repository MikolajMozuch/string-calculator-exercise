import exception.IncorrectInputFormatException;
import org.junit.jupiter.api.Test;
import services.CustomStringDelimiterParser;
import services.InputValidator;


import static org.junit.jupiter.api.Assertions.*;

public class InputValidatorTest {

    @Test
    public void addCommaSeparatorAtEndShouldThrowException() {
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                InputValidator.isSeparatorAtTheEnd("1,2,", CustomStringDelimiterParser.DELIMITER_COMMA));
        assertEquals("Input data cannot end with the separator", exception.getMessage());
    }

    @Test
    public void addNewlineSeparatorAtEndShouldThrowException() {
        var exception = assertThrows(IncorrectInputFormatException.class, ()
                -> InputValidator.isSeparatorAtTheEnd("1\n2\n", CustomStringDelimiterParser.DELIMITER_NEW_LINE));
        assertEquals("Input data cannot end with the separator", exception.getMessage());
    }
    // 6)
    @Test
    public void verifyNegativeNumbersInputContainsOneNegativeNumberThrowException() {
        int[] input = {1, -2, 3};
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                InputValidator.verifyNoNegativeNumbers(input));
        assertEquals("Negative number(s) not allowed: -2", exception.getMessage());
    }

    @Test
    public void verifyNegativeNumbersInputContainsMultipleNegativeNumbersThrowException() {
        int[] input = {1, -2, -3, -4};
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                InputValidator.verifyNoNegativeNumbers(input));
        assertEquals("Negative number(s) not allowed: -2, -3, -4", exception.getMessage());
    }

    @Test
    public void verifyNegativeNumbersInputContainsOnlyPositiveNumbersDoesNotThrowException() {
        int[] input = {1, 2, 3};
        assertDoesNotThrow(() -> InputValidator.verifyNoNegativeNumbers(input));
    }
}
