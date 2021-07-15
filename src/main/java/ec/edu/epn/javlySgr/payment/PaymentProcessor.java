package ec.edu.epn.javlySgr.payment;

import ec.edu.epn.javlySgr.payment.method.IPaymentMethod;

import java.text.ParseException;

public class PaymentProcessor {
    private PaymentGateway paymentGateway;

    public PaymentProcessor(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public boolean makePayment(double amount, IPaymentMethod paymentMethod) throws ParseException {
        if(!paymentMethod.validateData()) {
            return false;
        }

        PaymentRequest paymentRequest = new PaymentRequest(amount, paymentMethod);
        PaymentResponse paymentResponse = this.paymentGateway.requestPayment(paymentRequest);
        return paymentResponse.getPaymentStatus() == PaymentStatus.COMPLETED;
    }
}
