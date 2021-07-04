package GestionPagos;

public class CuentaBancaria {
    private String numero;
    private String nombre;
    private String banco;
    private Pago pago;

    public CuentaBancaria(String numero, String nombre, String banco){
        this.numero = numero;
        this.nombre = nombre;
        this.banco = banco;
    }

    public boolean solicitarDeposito(double monto){
           return pago.efectuarCobro(monto, this.numero);
    }

}
