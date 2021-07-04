package GestionPagos;

import PasarelaDePagos.PasarelaPagos;

import java.util.Date;

public class TarjetaDeCredito implements MetodoDePago{
    private String numero;
    private Date fechaExpiración;
    private int cvv;
    private String nombre;
    private String banco;
    private final Pago pago;

    public TarjetaDeCredito(String numero, Date fechaExpiración, int cvv, String nombre){
        this.numero = numero;
        this.fechaExpiración = fechaExpiración;
        this.cvv = cvv;
        this.nombre=nombre;
        this.pago = new PasarelaPagos();
    }
    public boolean solicitarPago(double monto){
       return pago.efectuarCobro(monto, this.numero);
    }
}
