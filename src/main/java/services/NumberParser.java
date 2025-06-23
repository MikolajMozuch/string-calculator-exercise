package services;

import java.util.Arrays;

public class NumberParser {

    private static final String DEFAULT_DELIMITER_REGEX = "[,\n]";

    public static int[] parse(String input) {
        return parseWithCustomDelimiter(input, DEFAULT_DELIMITER_REGEX);
    }

    public static int[] parseWithCustomDelimiter(String input, String delimiterRegex) {
        return Arrays.stream(input.split(delimiterRegex))
                .filter(s -> !s.isBlank())
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
