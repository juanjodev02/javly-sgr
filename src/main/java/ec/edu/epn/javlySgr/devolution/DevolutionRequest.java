package ec.edu.epn.javlySgr.devolution;

public class DevolutionRequest {
    private double amount;
    private BankAccount bankAccount;

    public DevolutionRequest(double amount, BankAccount bankAccount) {
        this.amount = amount;
        this.bankAccount = bankAccount;
    }

    public double getAmount() {
        return amount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
