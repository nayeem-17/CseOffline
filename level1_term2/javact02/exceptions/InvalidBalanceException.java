package exceptions;

public class InvalidBalanceException extends Exception {
    public InvalidBalanceException() {
        super("The account balance can’t be less than zero");
    }
}
