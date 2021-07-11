package GestionReservas;

import GestionPagos.MetodoDePago;

import java.util.ArrayList;
import java.util.Date;

public class ControladorReservas {
    private final ArrayList<Habitacion> habitaciones = new ArrayList<>();
    private final ArrayList<Reservacion> reservas = new ArrayList<>();

    public ControladorReservas(ArrayList<Habitacion> habitaciones) {
        this.habitaciones.addAll(habitaciones);
    }

    public int registrarReserva(Date fechaIngreso, Date fechaSalida, int numeroHabitacion, String nombreDeUsuario) {
        Habitacion habitacionExistente = null;

        for(Habitacion habitacion: this.habitaciones) {
            if (habitacion.getNumeroHabitacion() == numeroHabitacion) {
                habitacionExistente = habitacion;
                break;
            }
        }

        if (habitacionExistente == null) throw new Error("El número de habitación ingresado no es válido");

        if (comprobarFechas(fechaIngreso, fechaSalida, numeroHabitacion)) {
            Reservacion nuevaReserva = new Reservacion(
                    this.reservas.size() + 1,
                    fechaIngreso,
                    fechaSalida,
                    habitacionExistente,
                    nombreDeUsuario
            );
            this.reservas.add(nuevaReserva);
            return nuevaReserva.getNumeroReserva();
        } else {
            throw new Error("Something were wrong");
        }

    }

    private boolean comprobarFechas(Date fechaIngreso, Date fechaSalida, int numeroHabitacion) {
        boolean esValido = false;
        if (this.reservas.size() == 0) return true;
        for(Reservacion reservacion : this.reservas) {
            boolean estadoCancelada =reservacion.getEstado().equals("cancelada");
            int numeroHabitacionReservada = reservacion.getHabitacion().getNumeroHabitacion();
            if(!estadoCancelada && numeroHabitacionReservada == numeroHabitacion) {
                if(fechaIngreso.after(reservacion.getFechaIngreso()) && fechaIngreso.before(reservacion.getFechaSalida())){
                    // Date is between reserved days, is not possible to create a new reservation
                    throw new Error("La habitación no está disponible en las fechas seleccionadas");
                }
                if(fechaSalida.after(reservacion.getFechaIngreso()) && fechaSalida.before(reservacion.getFechaSalida())) {
                    //Out date overlaps the checkin day of other reservation
                    throw new Error("La habitación no esta disponible en las fechas seleccionadas");
                }
                esValido = true;
            }
        }
        return esValido;
    }

    public ArrayList<Reservacion> getReservasPorUsuario(String nombreDeUsuario) {
        ArrayList<Reservacion> reservacionsPorUsuario = new ArrayList<>();
        for(Reservacion reservacion : this.reservas) {
            boolean comprobacionNombreUsuario=reservacion.getNombreDeUsuario().equals(nombreDeUsuario);
            if (comprobacionNombreUsuario) {
                reservacionsPorUsuario.add(reservacion);
            }
        }
        return  reservacionsPorUsuario;
    }

    public Reservacion getReservaPorCodigo(int codigoReserva){
        int _codigoReserva = codigoReserva;
        for(Reservacion reservacion : this.reservas) {
            if (reservacion.getNumeroReserva() == _codigoReserva) {
                return reservacion;
            }
        }
        throw new Error("No se ha encontrado ninguna reserva con ese código");
    }

    public boolean pagarReserva(MetodoDePago metodoDePago, int codigoReserva) {
        Reservacion reservacionPagar = this.getReservaPorCodigo(codigoReserva);
        return reservacionPagar.pagarReservacion(metodoDePago);
    }
}
