package services;

import exception.IncorrectInputFormatException;

public interface StringCalculatorService {

    int add(String inputNumbers) throws IncorrectInputFormatException;

    int add(String... args) throws IncorrectInputFormatException;

}
