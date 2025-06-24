package services;

import exception.IncorrectInputFormatException;

/**
 * Interface for a String Calculator Service that provides methods to add numbers from strings.
 */
public interface StringCalculatorService {
    /**
     * Adds numbers from a single input string.
     * The input string can contain numbers separated by delimiters such as commas, newlines, or custom delimiters.
     *
     * @param inputNumbers the input string containing numbers to be added.
     * @return the sum of the numbers in the input string.
     * @throws exception.IncorrectInputFormatException if the input string contains invalid formatting or unexpected delimiters.
     */
    int add(String inputNumbers) throws IncorrectInputFormatException;
    /**
     * Adds numbers from multiple input strings.
     * Each input string can contain numbers separated by delimiters such as commas, newlines, or custom delimiters.
     *
     * @param args an array of input strings containing numbers to be added.
     * @return the sum of the numbers across all input strings.
     * @throws exception.IncorrectInputFormatException if any of the input strings contain invalid formatting or unexpected delimiters.
     */
    int add(String... args) throws IncorrectInputFormatException;

}
