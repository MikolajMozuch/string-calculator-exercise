package services;

import exception.IncorrectInputFormatException;

public class InputValidator {

    private static final String END_WITH_SEPARATOR_EXCEPTION_MESSAGE = "Input data cannot end with the separator";

    public static void isSeparatorAtTheEnd(String inputNumbers, String delimiter) throws IncorrectInputFormatException {
        if (delimiter.equals(CustomStringDelimiterParser.DELIMITER_DEFAULT)) {
            if (inputNumbers.endsWith(CustomStringDelimiterParser.DELIMITER_COMMA)
                    || inputNumbers.endsWith(CustomStringDelimiterParser.DELIMITER_NEW_LINE)) {
                throw new IncorrectInputFormatException(END_WITH_SEPARATOR_EXCEPTION_MESSAGE);
            }
        } else if (inputNumbers.endsWith(delimiter)) {
            throw new IncorrectInputFormatException(END_WITH_SEPARATOR_EXCEPTION_MESSAGE);
        }
    }
}
