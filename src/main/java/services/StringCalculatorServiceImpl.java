package services;

import exception.IncorrectInputFormatException;

import java.util.ArrayList;
import java.util.List;

public class StringCalculatorServiceImpl implements StringCalculatorService {

    @Override
    public int add(String inputNumbers) throws IncorrectInputFormatException {
        if (inputNumbers == null || inputNumbers.isEmpty()) {
            return 0;
        }
        List<Integer> numbers = extractAndValidateData(inputNumbers);
        return sumIntegers(numbers);
    }

    private List<Integer> extractAndValidateData(String inputNumbers) throws IncorrectInputFormatException {
        List<String> errors = new ArrayList<>();
        List<Integer> numbers = CustomStringDelimiterParser.parse(inputNumbers, errors);

        if (!errors.isEmpty()) {
            throw new IncorrectInputFormatException(String.join("\n", errors));
        }

        return numbers;
    }

    @Override
    public int add(String... args) throws IncorrectInputFormatException {
        int totalSum = 0;
        for (String input : args) {
            totalSum += add(input);
        }
        return totalSum;
    }

    private int sumIntegers(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);
    }

}
