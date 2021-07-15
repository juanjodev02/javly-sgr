package ec.edu.epn.javlySgr.payment;

public class PaymentResponse {
    private PaymentStatus paymentStatus;

    public PaymentResponse(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
