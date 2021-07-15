package ec.edu.epn.javlySgr.payment.method;

import java.text.ParseException;

public interface IPaymentMethod {
    public boolean validateData() throws ParseException;
}
