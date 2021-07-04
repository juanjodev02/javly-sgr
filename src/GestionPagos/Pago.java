package GestionPagos;

public interface Pago {

    public abstract boolean efectuarPago(double monto, String numero);
    public abstract boolean efectuarCobro(double monto, String numero);
}
