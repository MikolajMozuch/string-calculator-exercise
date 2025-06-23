package services;

import java.util.Arrays;

public class StringCalculatorServiceImpl implements StringCalculatorService {


    @Override
    public int add(String inputNumbers) {
        if (inputNumbers == null || inputNumbers.isEmpty()) {
            return 0;
        }
        return sumIntegers(parseCommaSeparatedIntegers(inputNumbers));
    }

    @Override
    public int add(String... args) {
        int totalSum = 0;
        for (String input : args) {
            totalSum += add(input);
        }
        return totalSum;
    }


    private int[] parseCommaSeparatedIntegers(String inputNumbers) {
        return Arrays.stream(inputNumbers.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private int sumIntegers(int[] numbers) {
        return Arrays.stream(numbers)
                .reduce(0, Integer::sum);
    }

}
