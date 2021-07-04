package GestionPagos;

public class PayPal implements MetodoDePago {
    private String linkPaypal;
    private Pago pago;


    public PayPal(String linkPaypal){
        this.linkPaypal = linkPaypal;

    }
    public String solicitarPago(double monto){
       return pago.efectuarCobro(monto, this.linkPaypal);
    }
}
