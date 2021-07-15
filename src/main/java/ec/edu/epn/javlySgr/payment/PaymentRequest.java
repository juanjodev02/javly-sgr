package ec.edu.epn.javlySgr.payment;

import ec.edu.epn.javlySgr.payment.method.IPaymentMethod;

public class PaymentRequest {
    private final double amount;
    private IPaymentMethod paymentMethod;

    public PaymentRequest(double amount, IPaymentMethod paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public IPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
}
