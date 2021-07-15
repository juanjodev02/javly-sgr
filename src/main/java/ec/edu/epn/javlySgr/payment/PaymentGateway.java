package ec.edu.epn.javlySgr.payment;

import ec.edu.epn.javlySgr.devolution.DevolutionRequest;

public interface PaymentGateway {
    public PaymentResponse requestPayment(PaymentRequest paymentRequest);
}
