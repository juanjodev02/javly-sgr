package ec.edu.epn.javlySgr.payment;

/**
 * PENDING: Your payment has not yet been sent to the bank or credit card processor.
 * COMPLETE: Your checking, savings or other bank account payment has been processed and accepted.
 * REJECTED: Your payment was not accepted when it was processed by the bank or credit card company.
 */
public enum PaymentStatus {
    PENDING,
    COMPLETED,
    REJECTED
}
