package services;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomStringDelimiterParser {

    public static final String DELIMITER_DEFAULT = "[,|\\n]"; // Default delimiters: comma, pipe, and newline
    public static final String DELIMITER_COMMA = ",";
    public static final String DELIMITER_NEW_LINE = "\n";
    public static final String DELIMITER_PREFIX = "//"; // Prefix for custom delimiter definition
    public static final String DELIMITER_PIPE = "|";

    public static List<Integer> parseAndValidateInput(String input, List<String> errors) {
        String delimiter = DELIMITER_DEFAULT;

        if (input.startsWith(DELIMITER_PREFIX)) {
            delimiter = extractCustomDelimiter(input);
            input = removeDelimiterDefinition(input, delimiter);
        }

        InputValidator.validateInput(input, delimiter, errors);

        return parseWithCustomDelimiter(input, delimiter, errors);
    }

    public static List<Integer> parseWithCustomDelimiter(String input, String delimiterRegex, List<String> errors) {
        String regex = delimiterRegex.equals(DELIMITER_DEFAULT) ? delimiterRegex : Pattern.quote(delimiterRegex);

        List<Integer> result;
        try { // Attempt to parse numbers using the specified delimiter
            result = parseNumbersByDelimiter(input, regex);
        } catch (NumberFormatException e) { // Fallback to salvage valid numbers if parsing fails
            result = fallbackParseNumbers(input);
        }
        return result;
    }

    public static List<Integer> fallbackParseNumbers(String input) {
        return Arrays.stream(input.split(DELIMITER_DEFAULT))
                .filter(s -> !s.isBlank()) //removes empty strings
                .flatMap(s -> {
                    try {
                        return Stream.of(Integer.parseInt(s));
                    } catch (NumberFormatException e) {
                        return Stream.empty();
                    }
                })
                .collect(Collectors.toList());
    }

    public static List<Integer> parseNumbersByDelimiter(String input, String delimiterRegex) {
        return Arrays.stream(input.split(delimiterRegex))
                .filter(s -> !s.isBlank())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
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
        return input.substring(DELIMITER_PREFIX.length() + delimiter.length() + 1);
    }

}
