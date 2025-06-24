package services;

import exception.IncorrectInputFormatException;

import java.util.ArrayList;
import java.util.List;

public class StringCalculatorServiceImpl implements StringCalculatorService {

    private static final int MAX_ALLOWED_NUMBER = 1000;

    @Override
    public int add(String inputNumbers) throws IncorrectInputFormatException {
        if (inputNumbers == null || inputNumbers.isEmpty()) {
            return 0;
        }
        List<Integer> numbers = extractAndValidateData(inputNumbers);
        return sumIntegers(numbers);
    }

    @Override
    public int add(String... args) throws IncorrectInputFormatException {
        int totalSum = 0;
        for (String input : args) {
            totalSum += add(input);
        }
        return totalSum;
    }

    private List<Integer> extractAndValidateData(String inputNumbers) throws IncorrectInputFormatException {
        List<String> errors = new ArrayList<>();
        List<Integer> numbers = CustomStringDelimiterParser.parseAndValidateInput(inputNumbers, errors);

        if (!errors.isEmpty()) {
            throw new IncorrectInputFormatException(String.join("\n", errors));
        }

        return numbers;
    }

    private int sumIntegers(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n <= MAX_ALLOWED_NUMBER)
                .reduce(0, Integer::sum);
    }

}
