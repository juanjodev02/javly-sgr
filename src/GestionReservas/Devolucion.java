package GestionReservas;

import GestionPagos.CuentaBancaria;

public class Devolucion {
    private Reservacion reservacion;
    private CuentaBancaria cuentaBancaria;

    public Devolucion(Reservacion reservacion, CuentaBancaria cuentaBancaria) {
        this.reservacion = reservacion;
        this.cuentaBancaria = cuentaBancaria;
    }

    public boolean solicitarDevolucion() {
        double monto = this.reservacion.getPrecio();
        if (cuentaBancaria.solicitarDeposito(monto)) {
            reservacion.setEstado("cancelada");
            return true;
        } else{
            throw new Error ("Algo ha sucedido, intentelo de nuevo");
        }
    }
}
