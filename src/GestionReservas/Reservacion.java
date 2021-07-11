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
        this.precio = calcularPrecio();
    }

    private double calcularPrecio () {
        return this.habitacion.getPrecio() * calcularDias();
    }

    private int calcularDias () {
        Date date1 = this.fechaIngreso;
        Date date2 = this.fechaSalida;
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
        if (metodoDePago.solicitarPago(this.precio)) {
            this.estado = "pagada";
            return true;
        } else {
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
