import exception.IncorrectInputFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.StringCalculatorService;
import services.StringCalculatorServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorServiceTest {

    private static StringCalculatorService stringCalculatorService;
    int result;

    @BeforeEach
    public void setUp() {
        stringCalculatorService = new StringCalculatorServiceImpl();
    }

    // 1)
    @Test
    public void addEmptyStringReturnZero() throws IncorrectInputFormatException {
        assertEquals(0, stringCalculatorService.add(""));
    }

    @Test
    public void addOneNumberReturnSameNumber() throws IncorrectInputFormatException {
        assertEquals(1, stringCalculatorService.add("1"));
    }

    @Test
    public void addMultipleValidNumbersReturnSum() throws IncorrectInputFormatException {
        result = stringCalculatorService.add("6,4");
        assertEquals(10, result);
    }

    // 2)
    @Test
    public void addMultipleValidInputsReturnSum() throws IncorrectInputFormatException {
        result = stringCalculatorService.add("6,4", "2,4", "8,2", "2", "");
        assertEquals(28, result);
    }

    // 3)
    @Test
    public void addNewlineAndCommaSeparatorsReturnSum() throws IncorrectInputFormatException {
        result = stringCalculatorService.add("1\n2\n3");
        assertEquals(6, result);
    }

    //5)
    @Test
    public void addWithCustomSemicolonDelimiterReturnsSum() throws IncorrectInputFormatException {
        result = stringCalculatorService.add("//;\n1;3");
        assertEquals(4, result);
    }

    @Test
    public void addWithCustomPipeDelimiterReturnsSum() throws IncorrectInputFormatException {
        result = stringCalculatorService.add("//|\n1|2|3");
        assertEquals(6, result);
    }

    @Test
    public void addWithCustomStringDelimiterReturnsSum() throws IncorrectInputFormatException {
        result = stringCalculatorService.add("//sep\n2sep5");
        assertEquals(7, result);
    }

    @Test
    public void addWithMixedDelimitersThrowsIncorrectInputFormatException() {
        IncorrectInputFormatException exception = assertThrows(
                IncorrectInputFormatException.class,
                () -> stringCalculatorService.add("//|\n1|2,3")
        );
        assertEquals("'|' expected but ',' found at position 3.", exception.getMessage());
    }
    @Test
    public void testAddWithMultipleErrorsAllErrorReturnMessageSeparatedByNewlines() {
        String input = "//|\n1|2,-3";

        IncorrectInputFormatException exception = assertThrows(
                IncorrectInputFormatException.class,
                () -> stringCalculatorService.add(input)
        );

        String expectedMessage = "Negative number(s) not allowed: -3\n'|' expected but ',' found at position 3.";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
