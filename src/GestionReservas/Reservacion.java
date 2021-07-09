package GestionReservas;

import GestionPagos.MetodoDePago;

import java.util.Date;

public class Reservacion {
    private int numeroReserva;
    private Date fechaIngreso;
    private Date fechaSalida;
    private Habitacion habitacion;
    private double precio;
    private String estado;
    private String nombreDeUsuario;

    public Reservacion(int numeroReserva, Date fechaIngreso, Date fechaSalida, Habitacion habitacion, String nombreDeUsuario) {
        this.numeroReserva = numeroReserva;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.habitacion = habitacion;
        this.estado = "pendiente";
        this.nombreDeUsuario = nombreDeUsuario;
        this.precio = this.habitacion.getPrecio() * calcularDias(this.fechaIngreso, this.fechaSalida);
    }

    private int calcularDias (Date date1, Date date2) {
        long date1InMs = date1.getTime();
        long date2InMs = date2.getTime();
        long timeDiff = 0;
        if(date1InMs > date2InMs) {
            timeDiff = date1InMs - date2InMs;
        } else {
            timeDiff = date2InMs - date1InMs;
        }
        return (int) (timeDiff / (1000 * 60 * 60* 24));
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public boolean pagarReservacion(MetodoDePago metodoDePago) {
        boolean resultado =  metodoDePago.solicitarPago(this.precio);
        if (resultado) {
            this.estado = "pagada";
            return true;
        } else {
          /* throw new Error("El banco rechazó el pago!");*/
            System.out.println("El banco rechazó su pago!");
            return  false;
        }
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public int getNumeroReserva() {
        return numeroReserva;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }
}
