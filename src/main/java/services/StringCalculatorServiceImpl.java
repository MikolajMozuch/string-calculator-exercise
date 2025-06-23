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
