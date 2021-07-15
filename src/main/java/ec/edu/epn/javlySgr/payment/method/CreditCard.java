package ec.edu.epn.javlySgr.payment.method;

import java.text.ParseException;

public class CreditCard implements IPaymentMethod {
    private long number;
    private String ownerName;
    private String expirationDate;
    private int cvv;
    private String company;

    public CreditCard(long number, String ownerName, String expirationDate, int cvv, String company) {
        this.number = number;
        this.ownerName = ownerName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.company = company;
    }

    @Override
    public boolean validateData() throws ParseException {
        Validator validator = new Validator();
        boolean validNumber = validator.isValidCardNumber(this.number);
        boolean validExpirationDate = validator.isValidExpirationDate(this.expirationDate);
        boolean validCvv = validator.isValidCvv(this.cvv);
        return validNumber && validExpirationDate && validCvv;
    }
}
