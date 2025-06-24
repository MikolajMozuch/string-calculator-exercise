package services;

public interface StringCalculatorService {

    int add(String inputNumbers) throws exception.IncorrectInputFormatException;

    int add(String... args) throws exception.IncorrectInputFormatException;

}
