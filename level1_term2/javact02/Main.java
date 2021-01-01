import exceptions.InvalidAmountException;
import exceptions.InvalidBalanceException;

public class Main {
    public static void main(String[] args) {
        try {
            Account account = new Account(-12);
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        }
        try {
            Account account1 = new Account(42);
            account1.credit(10);
            try {
                account1.debit(60);
            } catch (InvalidBalanceException e) {
                System.out.println(e.getMessage());
            }

        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        }

    }
}