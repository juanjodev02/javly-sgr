package GestionPagos;

public interface Pago {

    public abstract String efectuarPago(double monto, String numero);
    public abstract String efectuarCobro(double monto, String numero);
}
