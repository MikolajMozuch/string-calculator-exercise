package exception;

public class IncorrectInputFormatException extends Exception{
    public IncorrectInputFormatException(String errorMessage) {
        super(errorMessage);
    }
}