package ec.edu.epn.javlySgr.payment.method;

public class PayPal implements IPaymentMethod {
    private  String username;
    private String paypalUrl;

    public PayPal(String username, String paypalUrl) {
        this.username = username;
        this.paypalUrl = paypalUrl;
    }

    @Override
    public boolean validateData() {
        return this.paypalUrl.matches("tring regex = \"<\\\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]>\";");
    }
}
