package services;

import exception.IncorrectInputFormatException;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomStringDelimiterParser {

    public static final String DELIMITER_DEFAULT = ",|\\||\n";
    public static final String DELIMITER_COMMA = ",";
    public static final String DELIMITER_NEW_LINE = "\n";
    public static final String DELIMITER_PREFIX = "//";
    public static final String DELIMITER_PIPE = "|";

    public static int[] parse(String input) throws IncorrectInputFormatException {
        String delimiter = DELIMITER_DEFAULT;

        if (input.startsWith(DELIMITER_PREFIX)) {
            delimiter = extractCustomDelimiter(input);
            input = removeDelimiterDefinition(input, delimiter);
            validateDelimiterUsage(input, delimiter);
        }

        return parseWithCustomDelimiter(input, delimiter);
    }

    public static int[] parseWithCustomDelimiter(String input, String delimiterRegex) throws IncorrectInputFormatException {
        InputValidator.isSeparatorAtTheEnd(input, delimiterRegex);
        try {
            if (!delimiterRegex.equals(DELIMITER_DEFAULT)) {
                delimiterRegex = Pattern.quote(delimiterRegex);
            }
            return Arrays.stream(input.split(delimiterRegex))
                    .filter(s -> !s.isBlank())
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException e) {
            throw new IncorrectInputFormatException("Invalid number format.");
        }
    }

    private static String extractCustomDelimiter(String input) {
        String pattern = "^//(.*?)\\n";
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return DELIMITER_DEFAULT;
    }

    private static String removeDelimiterDefinition(String input, String delimiter) {
        return input.substring(DELIMITER_PREFIX.length() + delimiter.length() + 1); // +1 for '\n'
    }

    private static void validateDelimiterUsage(String body, String delimiter) throws IncorrectInputFormatException {
        Pattern validSplit = Pattern.compile(Pattern.quote(delimiter) + "|\\d+");
        Matcher matcher = validSplit.matcher(body);
        int index = 0;

        while (matcher.find()) {
            if (matcher.start() != index) {
                char unexpected = body.charAt(index);
                throw new IncorrectInputFormatException("'" + delimiter + "' expected but '" + unexpected
                        + "' found at position " + index + ".");
            }
            index = matcher.end();
        }
    }
}
