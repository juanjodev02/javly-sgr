package GestionPagos;

import java.util.Date;

public class TarjetaDeCredito implements MetodoDePago{
    private String numero;
    private Date fechaExpiración;
    private int cvv;
    private String nombre;
    private String banco;
    private Pago pago;

    public TarjetaDeCredito(String numero, Date fechaExpiración, int cvv, String nombre, String banco){
        this.numero = numero;
        this.fechaExpiración = fechaExpiración;
        this.cvv = cvv;
        this.nombre=nombre;
        this.banco = banco;
    }
    public String solicitarPago(double monto){
       return pago.efectuarCobro(monto, this.numero);
    }
}
