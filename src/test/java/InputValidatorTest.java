import org.junit.jupiter.api.Test;
import services.CustomStringDelimiterParser;
import services.InputValidator;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InputValidatorTest {
    @Test
    public void addCommaSeparatorAtEndShouldAddError() {
        List<String> errors = new ArrayList<>();
        InputValidator.checkEndsWithDelimiter("1,2,", CustomStringDelimiterParser.DELIMITER_COMMA, errors);
        assertTrue(errors.contains("Input data cannot end with the separator"));
    }

    @Test
    public void addNewlineSeparatorAtEndShouldAddError() {
        List<String> errors = new ArrayList<>();
        InputValidator.checkEndsWithDelimiter("1\n2\n", CustomStringDelimiterParser.DELIMITER_NEW_LINE, errors);
        assertTrue(errors.contains("Input data cannot end with the separator"));
    }

    //6)
    @Test
    public void verifyNegativeNumbersInputContainsOneNegativeNumberAddsError() {
        List<String> errors = new ArrayList<>();
        InputValidator.checkNegativeNumbers("1,-2", CustomStringDelimiterParser.DELIMITER_COMMA, errors);
        assertTrue(errors.contains("Negative number(s) not allowed: -2"));
    }

    @Test
    public void verifyNegativeNumbersInputContainsMultipleNegativeNumbersAddsError() {
        List<String> errors = new ArrayList<>();
        InputValidator.checkNegativeNumbers("2,-4,-9", CustomStringDelimiterParser.DELIMITER_COMMA, errors);
        assertTrue(errors.contains("Negative number(s) not allowed: -4, -9"));
    }

    @Test
    public void verifyNegativeNumbersInputContainsOnlyPositiveNumbersAddsNoError() {
        List<String> errors = new ArrayList<>();
        InputValidator.checkNegativeNumbers("1,2,3", CustomStringDelimiterParser.DELIMITER_COMMA, errors);
        assertTrue(errors.isEmpty());
    }
}