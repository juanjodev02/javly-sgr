package GestionPagos;

import PasarelaDePagos.PasarelaPagos;

public class PayPal implements MetodoDePago {
    private String linkPaypal;
    private Pago pago;


    public PayPal(String linkPaypal){
        this.linkPaypal = linkPaypal;
        this.pago = new PasarelaPagos();
    }
    public boolean solicitarPago(double monto){
       return pago.efectuarCobro(monto, this.linkPaypal);
    }
}
