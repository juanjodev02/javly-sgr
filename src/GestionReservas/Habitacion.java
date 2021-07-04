package GestionReservas;

public class Habitacion {
    private int numeroHabitacion;
    private String descripcion;
    private double precio;

    public Habitacion(int numeroHabitacion, String descripcion, double precio) {
        this.numeroHabitacion = numeroHabitacion;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }
}
