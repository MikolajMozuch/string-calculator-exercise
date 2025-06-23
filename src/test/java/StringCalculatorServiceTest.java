import org.junit.jupiter.api.Test;
import services.StringCalculatorService;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringCalculatorServiceTest {

    private static StringCalculatorService stringCalculatorService;
    int result;

    // 1)
    @Test
    public void add_emptyString_ReturnZero() {
        assertEquals(0, stringCalculatorService.add(""));
    }

    @Test
    public void add_oneNumber_returnSameNumber() {
        assertEquals(1, stringCalculatorService.add("1"));
    }

    @Test
    public void add_multipleValidNumbers_returnSum() {
        result = stringCalculatorService.add("6,4");
        assertEquals(10, result);
    }
}
