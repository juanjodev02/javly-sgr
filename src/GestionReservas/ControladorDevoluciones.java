package GestionReservas;

import GestionPagos.CuentaBancaria;

import java.util.ArrayList;

public class ControladorDevoluciones {
    private ArrayList<Devolucion> devoluciones = new ArrayList<>();

    public void solicitarDevolucion(Reservacion reservacion, CuentaBancaria cuentaBancaria) {
        double monto = reservacion.getPrecio();
        cuentaBancaria.solicitarDeposito(monto);
        reservacion.setEstado("cancelada");
    }
}
