import GestionPagos.CuentaBancaria;
import GestionPagos.MetodoDePago;
import GestionPagos.TarjetaDeCredito;
import GestionReservas.ControladorReservas;
import GestionReservas.Devolucion;
import GestionReservas.Habitacion;
import GestionReservas.Reservacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Main {
    public static void main(String[] args) throws ParseException {
        ArrayList<Habitacion> nuevasHabitaciones = new ArrayList<>();
        nuevasHabitaciones.add(new Habitacion(1, "Habitación para dos personas con vista al mar", 50.00));
        nuevasHabitaciones.add(new Habitacion(2, "Habitación matrimonial simple", 80.00));
        nuevasHabitaciones.add(new Habitacion(3, "Habitación triple con vista al mar", 150.00));
        nuevasHabitaciones.add(new Habitacion(4, "Habitación simple con vista al mar", 50.00));

        ControladorReservas controladorReservas = new ControladorReservas(nuevasHabitaciones);

        try {

            System.out.println("CASO DE PRUEBA 1: REALIZAR RESERVACION");
            System.out.println("Bienvenido - javly-sgr");
            System.out.println("1. Reservar habitación \n2. Cancelar reservación");
            System.out.println("ELIJA UNA OPCIÓN");
            System.out.println("Opción número 1 seleccionada");
            System.out.println("HABITACIONES REGISTRADAS");
            for (Habitacion habitacion : nuevasHabitaciones) {
                System.out.println("El código de habitación es: " + habitacion.getNumeroHabitacion() + "\n" +
                        "Descripción: " + habitacion.getDescripcion() + "\n" +
                        "Precio: " + habitacion.getPrecio() + "\n");
            }
            System.out.println("Para reservar una habitación, debe conocer el código de la misma");
            System.out.println("Ingrese el código de la habitación que deseas reservar:");
            int codigoHabitacion = 1;
            System.out.println(codigoHabitacion);
            System.out.println("Ingrese la fecha de entrada: (dd/mm/yyyy)");
            String inDate = "10/10/2021";
            System.out.println(inDate);
            Date parsedInDate = new SimpleDateFormat("dd/MM/yyy").parse(inDate);
            System.out.println("Ingrese la fecha de salida: (dd/mm/yyyy)");
            String outDate = "28/10/2021";
            System.out.println(outDate);
            Date parsedOutDate = new SimpleDateFormat("dd/MM/yyy").parse(outDate);
            System.out.println("Ingrese el nombre con el que la reserva se registrará:");
            String nombreUsuario = "Luis Morales";
            System.out.println(nombreUsuario);
            int codigoReserva = controladorReservas.registrarReserva(parsedInDate, parsedOutDate, codigoHabitacion, nombreUsuario);
            System.out.println("La reserva se ha registrado correctamente, ahora se debe efectuar el pago de la misma");
            System.out.println("ELIJA UN MÉTODO DE PAGO:");
            System.out.println("1. Tarjeta de crédito");
            System.out.println("2. Paypal");
            int opcionDePago = 1;
            System.out.println("Método de pago seleccionado: Tarjeta de Crédito");
            if (opcionDePago == 1) {
                System.out.println("INGRESO DE DATOS DE LA TARJETA DE CRÉDITO");
                System.out.println("Ingrese el número de tarjeta:");
                String numeroDeTarjeta = "5454";
                System.out.println(numeroDeTarjeta);
                System.out.println("Ingrese la fecha de expiración: (dd/mm/yyyy)");
                String unparsedExpirationDate = "05/11/2027";
                System.out.println(unparsedExpirationDate);
                Date parsedExpirationDate = new SimpleDateFormat("dd/MM/yyy").parse(unparsedExpirationDate);
                System.out.println("Ingrese el código cvv de la tarjeta:");
                int cvv = 1468936;
                System.out.println(cvv);
                System.out.println("Ingrese el nombre del propietario de la tarjeta:");
                String ownerName = "Luis Morales";
                System.out.println(ownerName);
                MetodoDePago tarjeta = new TarjetaDeCredito(numeroDeTarjeta, parsedExpirationDate, cvv, ownerName);
                controladorReservas.pagarReserva(tarjeta, codigoReserva);
            }
            System.out.println("Gracias por preferirnos, aquí esta su ticket de reserva, recuerde que con el código de su reserva puede acercase al hotel el dáa del checkin.");
            Reservacion reservacion = controladorReservas.getReservaPorCodigo(codigoReserva);
            System.out.println("Nombre de la reserva: " + reservacion.getNombreDeUsuario() + "\n" +
                    "Código de reserva: " + reservacion.getNumeroReserva() + "\n" +
                    "Fecha de entrada: " + reservacion.getFechaIngreso().toString() + "\n" +
                    "Fecha de salida: " + reservacion.getFechaSalida().toString() + "\n" +
                    "Costo: " + reservacion.getPrecio() + "\n" +
                    "Estado:" + reservacion.getEstado() + "\n"
            );


            System.out.println("CASO DE PRUEBA 2: CANCELAR RESERVACION");
            System.out.println("Bienvenido - javly-sgr");
            System.out.println("1. Reservar habitación \n2. Cancelar reservación");
            System.out.println("ELIJA UNA OPCIÓN");
            System.out.println("Opción número 2 seleccionada");
            System.out.println("INGRESO DE DATOS PARA LA REALIZACIÓN DE LA DEVOLUCIÓN");
            System.out.println("Ingrese el número de reserva: ");
            int numeroReserva = 1451;
            System.out.println(numeroReserva);
            System.out.println("Ingrese el número de la cuenta bancaria: ");
            String numeroCuenta = "154156";
            System.out.println(numeroCuenta);
            System.out.println("Ingrese su nombre: ");
            String nombre = "Martin Paredes";
            System.out.println(nombre);
            System.out.println("Ingrese el nombre del banco: ");
            String nombreBanco = "Banco Pichincha";
            System.out.println(nombreBanco);
            CuentaBancaria cuentaBancaria = new CuentaBancaria(numeroCuenta, nombre, nombreBanco);
            Devolucion devolucion = new Devolucion(controladorReservas.getReservaPorCodigo(numeroReserva), cuentaBancaria);
            devolucion.solicitarDevolucion();
        } catch (Error | Exception e) {
            System.out.println(e.getMessage());
        }
    }}
