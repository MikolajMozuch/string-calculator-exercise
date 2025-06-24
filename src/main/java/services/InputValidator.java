package services;

import exception.IncorrectInputFormatException;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InputValidator {

    private static final String END_WITH_SEPARATOR_EXCEPTION_MESSAGE = "Input data cannot end with the separator";

    public static void isSeparatorAtTheEnd(String inputNumbers, String delimiter) throws IncorrectInputFormatException {
        if (delimiter.equals(CustomStringDelimiterParser.DELIMITER_DEFAULT)) {
            if (inputNumbers.endsWith(CustomStringDelimiterParser.DELIMITER_COMMA)
                    || inputNumbers.endsWith(CustomStringDelimiterParser.DELIMITER_NEW_LINE)
            ||inputNumbers.endsWith(CustomStringDelimiterParser.DELIMITER_PIPE)) {
                throw new IncorrectInputFormatException(END_WITH_SEPARATOR_EXCEPTION_MESSAGE);
            }
        } else if(inputNumbers.endsWith(delimiter)) {
                throw new IncorrectInputFormatException(END_WITH_SEPARATOR_EXCEPTION_MESSAGE);
        }
    }
    public static void verifyNoNegativeNumbers(int[] numbers) throws IncorrectInputFormatException {
        String negativeNumbers = Arrays.stream(numbers)
                .filter(n -> n < 0)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));

        if (!negativeNumbers.isEmpty()) {
            throw new IncorrectInputFormatException("Negative number(s) not allowed: " + negativeNumbers);
        }
    }
}
