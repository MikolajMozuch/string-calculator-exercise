import exception.IncorrectInputFormatException;
import org.junit.jupiter.api.Test;
import services.CustomStringDelimiterParser;
import services.InputValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
