package services;

import exception.IncorrectInputFormatException;

import java.util.Arrays;

public class CustomStringDelimiterParser {

    public static final String DELIMITER_DEFAULT = ",|\n";
    public static final String DELIMITER_COMMA = ",";
    public static final String DELIMITER_NEW_LINE = "\n";
    public static int[] parse(String input) throws IncorrectInputFormatException {
        return parseWithCustomDelimiter(input, DELIMITER_COMMA);
    }

    public static int[] parseWithCustomDelimiter(String input, String delimiterRegex) throws IncorrectInputFormatException {
        InputValidator.isSeparatorAtTheEnd(input,delimiterRegex);
        try {
            return Arrays.stream(input.split(delimiterRegex))
                    .filter(s -> !s.isBlank())
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException e) {
            throw new IncorrectInputFormatException("Invalid number format.");
        }
    }
}
