import exceptions.InvalidAmountException;
import exceptions.InvalidBalanceException;

public class Account {
    private double balance;

    public Account(double balance) throws InvalidAmountException {
        if (balance < 0) {
            throw new InvalidAmountException();
        }
        this.balance = balance;
    }

    void debit(double amount) throws InvalidBalanceException, InvalidAmountException {
        if (amount < 0) {
            throw new InvalidAmountException();
        }
        if (amount > this.balance) {
            throw new InvalidBalanceException();
        } else {
            this.balance -= amount;
        }
    }

    void credit(double amount) throws InvalidAmountException {
        if (amount < 0) {
            throw new InvalidAmountException();
        } else
            this.balance += amount;
    }

    public double getBalance() {
        return this.balance;
    }
}
