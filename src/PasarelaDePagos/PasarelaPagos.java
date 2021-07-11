package PasarelaDePagos;
import GestionPagos.Pago;
public class PasarelaPagos implements Pago{
    /**
     * Esta función es para devolver el dinero a los clientes
     * @param monto
     * @param numero
     * @return
     */
    public boolean efectuarPago(double monto, String numero) { return recibirRespuesta(); }

    /**
     * Esta función es para los cobros de clientes por una reservación
     * @param monto
     * @param numero
     * @return
     */
    public boolean efectuarCobro(double monto, String numero) {
        return recibirRespuesta();
    }

    public boolean recibirRespuesta(){return (Math.random() * 10) % 2 == 0; }
}


