package services;

import exception.IncorrectInputFormatException;

import java.util.Arrays;

public class StringCalculatorServiceImpl implements StringCalculatorService {

    @Override
    public int add(String inputNumbers) throws IncorrectInputFormatException {
        if (inputNumbers == null || inputNumbers.isEmpty()) {
            return 0;
        }
        int[] numbers = CustomStringDelimiterParser.parse(inputNumbers);
        InputValidator.verifyNoNegativeNumbers(numbers);
        return sumIntegers(numbers);
    }

    @Override
    public int add(String... args)  throws IncorrectInputFormatException{
        int totalSum = 0;
        for (String input : args) {
            totalSum += add(input);
        }
        return totalSum;
    }

    private int sumIntegers(int[] numbers) {
        return Arrays.stream(numbers)
                .reduce(0, Integer::sum);
    }

}
