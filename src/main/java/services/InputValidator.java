package services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputValidator {

    private static final String END_WITH_SEPARATOR_EXCEPTION_MESSAGE = "Input data cannot end with the separator";

    public static void validateInput(String input, String delimiter, List<String> errors) {
        if (input == null || input.isEmpty()) {
            return;
        }
        checkEndsWithDelimiter(input, delimiter, errors);
        checkNegativeNumbers(input, delimiter, errors);
        validateDelimiterUsage(input, delimiter, errors);
    }

    public static void checkEndsWithDelimiter(String input, String delimiter, List<String> errors) {
        if (delimiter.equals(CustomStringDelimiterParser.DELIMITER_DEFAULT)) {
            if (input.endsWith(CustomStringDelimiterParser.DELIMITER_COMMA)
                    || input.endsWith(CustomStringDelimiterParser.DELIMITER_NEW_LINE)
                    || input.endsWith(CustomStringDelimiterParser.DELIMITER_PIPE)) {
                errors.add(END_WITH_SEPARATOR_EXCEPTION_MESSAGE);
            }
        } else if (input.endsWith(delimiter)) {
            errors.add(END_WITH_SEPARATOR_EXCEPTION_MESSAGE);
        }
    }

    public static void checkNegativeNumbers(String input, String delimiter, List<String> errors) {
        List<Integer> numbers = parseNumbersForNegativeCheck(input, delimiter);
        String negativeNumbers = numbers.stream()
                .filter(n -> n < 0)
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        if (!negativeNumbers.isEmpty()) {
            errors.add("Negative number(s) not allowed: " + negativeNumbers);
        }
    }

    private static void validateDelimiterUsage(String input, String delimiter, List<String> errors) {
        Pattern pattern = delimiter.equals(CustomStringDelimiterParser.DELIMITER_DEFAULT)
                ? Pattern.compile(Pattern.quote(CustomStringDelimiterParser.DELIMITER_DEFAULT))
                : Pattern.compile("-?\\d+|" + Pattern.quote(delimiter));
        Matcher matcher = pattern.matcher(input);
        int index = 0;

        while (matcher.find()) {
            if (matcher.start() != index) {
                char unexpectedChar = input.charAt(index);
                errors.add("'" + delimiter + "' expected but '" + unexpectedChar + "' found at position " + index + ".");
                return;
            }
            index = matcher.end();
        }
    }

    private static List<Integer> parseNumbersForNegativeCheck(String input, String delimiter) {
        String delimiterRegex = delimiter.equals(CustomStringDelimiterParser.DELIMITER_DEFAULT)
                ? CustomStringDelimiterParser.DELIMITER_DEFAULT
                : Pattern.quote(delimiter);

        List<Integer> numbers;
        try {
            numbers = CustomStringDelimiterParser.parseNumbersByDelimiter(input, delimiterRegex);
        } catch (NumberFormatException e) {
            numbers = CustomStringDelimiterParser.fallbackParseNumbers(input);
        }
        return numbers;
    }
}
