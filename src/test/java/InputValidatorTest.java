import exception.IncorrectInputFormatException;
import org.junit.jupiter.api.Test;
import services.InputValidator;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputValidatorTest {

    @Test
    public void addCommaSeparatorAtEndShouldThrowException() {
        assertThrows(IncorrectInputFormatException.class, () ->
                InputValidator.validate("1,2,"));
    }

    @Test
    public void addNewlineSeparatorAtEndShouldThrowException() {
        assertThrows(IncorrectInputFormatException.class, ()
                -> InputValidator.validate("1\n2\n"));
    }
}
