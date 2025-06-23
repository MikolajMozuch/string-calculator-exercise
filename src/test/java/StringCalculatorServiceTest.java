import exception.IncorrectInputFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.StringCalculatorService;
import services.StringCalculatorServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        result = stringCalculatorService.add("1,2\n3");
        assertEquals(6, result);
    }
}
