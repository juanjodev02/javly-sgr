package GestionReservas;

public class Habitacion {
    private int numeroHabitacion;
    private String descripcion;
    private double precio;
    private Boolean estado;

    public Boolean getEstado() {
        return estado;
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
