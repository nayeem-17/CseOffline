package exceptions;

public class InvalidAmountException extends Exception {
    public InvalidAmountException() {
        super("The given amount can’t be negative");
    }
}
