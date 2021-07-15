package ec.edu.epn.javlySgr.payment;

import ec.edu.epn.javlySgr.payment.method.CreditCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.ParseException;

public class PaymentProcessorTest {

    private PaymentGateway successPaymentGateway = null;
    private PaymentGateway rejectPaymentGateway = null;

    @Before
    public void setUp() {
        this.successPaymentGateway = Mockito.mock(PaymentGateway.class);
        Mockito.when(this.successPaymentGateway.requestPayment(Mockito.any())).thenReturn(new PaymentResponse(PaymentStatus.COMPLETED));
    }

    @Test
    public void given_credit_card_when_is_correct_then_ok() throws ParseException {
        PaymentProcessor paymentProcessor = new PaymentProcessor(this.successPaymentGateway);
        CreditCard creditCard = new CreditCard(
                5196081888500645L,
                "Juan Jaramillo",
                "02/22",
                123,
                "visa"
        );
        boolean result = paymentProcessor.makePayment(50.00, creditCard);
        Assert.assertTrue(result);
    }

    @Test
    public void given_credit_card_when_is_incorrect_number_then_error() throws ParseException {
        PaymentProcessor paymentProcessor = new PaymentProcessor(this.successPaymentGateway);
        CreditCard creditCard = new CreditCard(
                5196081888500634L,
                "Juan Jaramillo",
                "02/22",
                123,
                "visa"
        );
        boolean result = paymentProcessor.makePayment(50.00, creditCard);
        Assert.assertFalse(result);
    }

    @Test
    public void given_credit_card_when_is_incorrect_expiration_date_then_error() throws ParseException {
        PaymentProcessor paymentProcessor = new PaymentProcessor(this.successPaymentGateway);
        CreditCard creditCard = new CreditCard(
                5196081888500645L,
                "Juan Jaramillo",
                "11/18",
                123,
                "visa"
        );
        boolean result = paymentProcessor.makePayment(50.00, creditCard);
        Assert.assertFalse(result);
    }

    @Test(expected = ParseException.class)
    public void given_credit_card_when_is_incorrect_expiration_format_date_then_exception() throws ParseException {
        PaymentProcessor paymentProcessor = new PaymentProcessor(this.successPaymentGateway);
        CreditCard creditCard = new CreditCard(
                5196081888500645L,
                "Juan Jaramillo",
                "11-20",
                123,
                "visa"
        );
        boolean result = paymentProcessor.makePayment(50.00, creditCard);
        Assert.assertFalse(result);
    }

    @Test
    public void given_credit_card_when_is_incorrect_cvv_length_then_error() throws ParseException {
        PaymentProcessor paymentProcessor = new PaymentProcessor(this.successPaymentGateway);
        CreditCard creditCard = new CreditCard(
                5196081888500645L,
                "Juan Jaramillo",
                "11/18",
                1233,
                "visa"
        );
        boolean result = paymentProcessor.makePayment(50.00, creditCard);
        Assert.assertFalse(result);
    }
}