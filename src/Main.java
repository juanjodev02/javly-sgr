import GestionPagos.CuentaBancaria;
import GestionPagos.MetodoDePago;
import GestionPagos.PayPal;
import GestionPagos.TarjetaDeCredito;
import GestionReservas.ControladorReservas;
import GestionReservas.Devolucion;
import GestionReservas.Habitacion;
import GestionReservas.Reservacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Habitacion> nuevasHabitaciones = new ArrayList<>();
        nuevasHabitaciones.add(new Habitacion(1, "Habitacion para dos personas con vista al mar",  50.00));
        nuevasHabitaciones.add(new Habitacion(2, "Habitacion matrimonial simple",  80.00));
        nuevasHabitaciones.add(new Habitacion(3, "Habitacion triple con vista al mar",  150.00));
        nuevasHabitaciones.add(new Habitacion(4, "Habitacion simple con vista al mar",  50.00));

        ControladorReservas controladorReservas = new ControladorReservas(nuevasHabitaciones);

        Scanner scanner = new Scanner(System.in);
        try {
            while(true) {
                System.out.println("Bienvenido - javly-sgr");
                System.out.println("1. Reservar habitacion \n2. Consultar habitaciones \n3. Cancelar reservacion");
                System.out.println("Eliga una opcion (1, 2 o 3)");
                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Bienvenido - javly-sgr");
                        System.out.println("Para reservar una habitacion, debe conocer el codigo de la misma");
                        System.out.println("Ingrese el codigo de la habitacion que deseas reservar:");
                        int codigoHabitacion = scanner.nextInt();
                        System.out.println("Ingrese la fecha de entrada: (dd/mm/yyyy)");
                        String inDate = scanner.next();
                        Date parsedInDate = new SimpleDateFormat("dd/MM/yyy").parse(inDate);
                        System.out.println("Ingrese la fecha de salida: (dd/mm/yyyy)");
                        String outDate = scanner.next();
                        Date parsedOutDate = new SimpleDateFormat("dd/MM/yyy").parse(outDate);
                        System.out.println("Ingresa el nombre con el que la reserva se registrará:");
                        String nombreUsuario = scanner.next();
                        int codigoReserva = controladorReservas.registrarReserva(parsedInDate, parsedOutDate, codigoHabitacion, nombreUsuario);
                        System.out.println("La reserva se ha registrado correctamente, ahora se debe efectuar el pago de la misma");
                        System.out.println("Elige un metodo de pago:");
                        System.out.println("1. Tarjeta de credito");
                        System.out.println("2. Paypal");
                        int opcionDePago = scanner.nextInt();
                        if (opcionDePago == 1) {
                            System.out.println("Ingrese el numero de tarjeta:");
                            String numeroDeTarjeta = scanner.next();
                            System.out.println("Ingrese la fecha de expiración: (dd/mm/yyyy)");
                            String unparsedExpirationDate = scanner.next();
                            Date parsedExpirationDate = new SimpleDateFormat("dd/MM/yyy").parse(unparsedExpirationDate);
                            System.out.println("Ingrese el codigo cvv de la tarjeta:");
                            int cvv = scanner.nextInt();
                            System.out.println("Ingrese el nombre del propietario de la tarjeta:");
                            String ownerName = scanner.next();
                            MetodoDePago tarjeta = new TarjetaDeCredito(numeroDeTarjeta, parsedExpirationDate, cvv, ownerName);
                            controladorReservas.pagarReserva(tarjeta, codigoReserva);
                        } else {
                            if (opcionDePago == 2) {
                                System.out.println("Ingrese su nombre de usuario de PayPal:");
                                String paypalLink = scanner.nextLine();
                                MetodoDePago paypal = new PayPal(paypalLink);
                                controladorReservas.pagarReserva(paypal, codigoReserva);
                            }
                        }
                        System.out.println("Gracias por preferirnos, aquí esta su ticket de reserva, recuerde que con el código de su reserva puede hacercase al hotel el dia del checkin.");
                        Reservacion reservacion = controladorReservas.getReservaPorCodigo(codigoReserva);
                        System.out.println("Nombre de la reseva: " + reservacion.getNombreDeUsuario() + "\n" +
                                "Codigo de reserva: " + reservacion.getNumeroReserva() + "\n" +
                                "Fecha de entrada: " + reservacion.getFechaIngreso().toString() + "\n" +
                                "Fecha de salida: " + reservacion.getFechaSalida().toString() + "\n" +
                                "Costo: " + reservacion.getPrecio() + "\n" +
                                "Estado:" + reservacion.getEstado() + "\n"
                        );
                        break;
                    case 2:
                        System.out.println("Las habitaciones registradas son:");
                        for(Habitacion habitacion: nuevasHabitaciones) {
                            System.out.println("El número de habitación es: " + habitacion.getNumeroHabitacion() + "\n" +
                                    "Descripción: " + habitacion.getDescripcion() + "\n" +
                                    "Precio: " + habitacion.getPrecio() + "\n");
                        }
                        break;
                    case 3:
                        System.out.println("Ingrese los siguientes datos para realizar la Devolucion");
                        System.out.println("Ingrese el número de reserva");
                        int numeroReserva = scanner.nextInt();
                        System.out.println("Ingrese el número de la cuenta bancaria");
                        String numeroCuenta = scanner.next();
                        System.out.println("Ingrese su nombre ");
                        String nombre = scanner.next();
                        System.out.println("Ingrese el nombre del banco ");
                        String nombreBanco = scanner.next();
                        CuentaBancaria cuentaBancaria = new CuentaBancaria(numeroCuenta,nombre, nombreBanco );
                        Devolucion devolucion = new Devolucion(controladorReservas.getReservaPorCodigo(numeroReserva),cuentaBancaria );
                        devolucion.solicitarDevolucion();
                        break;

                }
            }
        } catch (Error | Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
