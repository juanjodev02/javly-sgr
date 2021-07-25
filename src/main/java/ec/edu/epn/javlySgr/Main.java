package ec.edu.epn.javlySgr;

import java.text.ParseException;
import java.util.ArrayList;
import org.mockito.Mockito;
import ec.edu.epn.javlySgr.client.Client;
import ec.edu.epn.javlySgr.payment.*;
import ec.edu.epn.javlySgr.payment.method.CreditCard;
import ec.edu.epn.javlySgr.payment.method.IPaymentMethod;
import ec.edu.epn.javlySgr.reservation.Reservation;
import ec.edu.epn.javlySgr.reservation.ReservationProcessor;
import ec.edu.epn.javlySgr.reservation.Room;
import ec.edu.epn.javlySgr.reservation.RoomStatus;
import ec.edu.epn.javlySgr.service.Service;
import ec.edu.epn.javlySgr.service.ServiceProcessor;
import ec.edu.epn.javlySgr.service.ServiceType;

public class Main {
    public static void main(String[] args) throws ParseException{
        ArrayList<Room> newRoom =  new ArrayList<Room>();
        newRoom.add(new Room(1,"Room for two people",50.00, RoomStatus.AVAILABLE));
        newRoom.add(new Room(2,"Single room with sea view",100.00, RoomStatus.AVAILABLE));
        newRoom.add(new Room(3,"Double room with sea view",200.00, RoomStatus.AVAILABLE));
        ReservationProcessor reservationProcessor =  new ReservationProcessor(newRoom);

        try {
            System.out.println("**** WELCOME TO JAVLY-SGR ****");
            System.out.println("\t\tROOMS LIST");
            for (Room room : newRoom) {
                System.out.println("Room number: " + room.getNumber() + "\n" +
                        "Description: " + room.getDescription() + "\n" +
                        "Price: " + room.getPrice() + "\n" +
                        "Room status " + room.getRoomStatus());
            }
            System.out.println("\t\tCUSTOMER INFORMATION");
            System.out.print("Enter your name: ");
            String name = "Lesly";
            System.out.println(name);
            System.out.print("Enter your lastname: ");
            String lastname = "Tipanluiza";
            System.out.println(lastname);
            System.out.print("Enter your email: ");
            String email = "lesly.tipanluiza@epn.edu.ec";
            System.out.println(email);
            System.out.print("Enter your password: ");
            String password = "1234";
            System.out.println(password);
            Client newClient = new Client(name, lastname, email, password);
            System.out.println("\t\tRESERVATION INFORMATION");
            System.out.print("Enter check in date (dd/mm/yy): ");
            String inDate = "17/10/2021";
            System.out.println(inDate);
            System.out.print("Enter check out date (dd/mm/yy): ");
            String outDate = "21/10/2021";
            System.out.println(outDate);
            System.out.print("Enter a room number: ");
            int number = 1;
            System.out.println(number);
            reservationProcessor.makeReservation(newClient,inDate,outDate , number);
            System.out.print("Do you want to add a service? (Y/N): ");
            String option = "Y";
            System.out.println(option);
            ServiceProcessor serviceProcessor = new ServiceProcessor(reservationProcessor);
            System.out.println("\t\tSERVICE INFORMATION");
            System.out.print("Enter your reservation code: ");
            int code = 1;
            System.out.println(code);
            System.out.println("Choose a service from the list:");
            System.out.println(" * ROOM SERVICE");
            System.out.println(" * BREAKFAST");
            System.out.println(" * DINNER");
            System.out.println(" * TRANSPORTATION");
            System.out.println(" * LIFE SECURE");
            ServiceType serviceType = ServiceType.BREAKFAST;
            System.out.println("Service selected: "+serviceType);
            String obs ="N/A";
            System.out.println("Observation: "+obs);
            Reservation  reservation  = reservationProcessor.getReservationByCode(code) ;
            serviceProcessor.addService(number,serviceType,obs);
            System.out.println("Full payment (reservation + service) : "+reservation.getPrice());
            System.out.println("Reservation has been registered successfully!");
            System.out.println("\t\tPAYMENT INFORMATION");
            System.out.println("Choose a payment method");
            System.out.println("1. Credit Card");
            System.out.println("2. Paypal");
            int paymentOption = 1;
            System.out.println("Method selected: "+1);
            if(paymentOption==1){
                System.out.println("\t\tCREDIT CARD INFORMATION");
                System.out.print("Enter the credit card number: ");
                long creditNumber = 5196081888500645L;
                System.out.println(creditNumber);
                System.out.print("Enter the owner name: ");
                String owner = "Lesly Tipanluiza";
                System.out.println(owner);
                System.out.print("Enter the expiration date: ");
                String expirationDate = "10/25";
                System.out.println(expirationDate);
                System.out.print("Enter the cvv: ");
                int cvv = 123;
                System.out.println(cvv);
                System.out.print("Enter the company: ");
                String company = "Visa";
                System.out.println(company);
                CreditCard creditCard = new CreditCard(creditNumber, owner, expirationDate, cvv,company);
                PaymentGateway paymentGateway=Mockito.mock(PaymentGateway.class);
                Mockito.when(paymentGateway.requestPayment(Mockito.any())).thenReturn(new PaymentResponse(PaymentStatus.COMPLETED));
                PaymentProcessor paymentProcessor= new PaymentProcessor(paymentGateway);
                paymentProcessor.makePayment(reservation.getPrice(),creditCard);
                System.out.println("Thanks for choosing us! Here is your ticket: ");
                System.out.println("\t\tTICKET INFORMATION");
                System.out.println("Reservation in the name of: "+ reservation.getClient().getName()+" "+ reservation.getClient().getLastName()+"\n"+
                                    "Reservation code: "+ reservation.getCode()+"\n"+
                                            "Check in date: "+ reservation.getCheckInDate()+"\n"+
                                            "Check out date: "+ reservation.getCheckOutDate()+"\n"+
                                            "Price: "+reservation.getPrice()+"\n"
                );
            }
        }catch (Error | Exception e){
            System.out.println(e.getMessage());
        }
    }
}
