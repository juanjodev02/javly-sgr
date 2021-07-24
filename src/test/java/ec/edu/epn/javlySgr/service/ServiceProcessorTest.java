package ec.edu.epn.javlySgr.service;

import ec.edu.epn.javlySgr.client.Client;
import ec.edu.epn.javlySgr.reservation.Reservation;
import ec.edu.epn.javlySgr.reservation.ReservationProcessor;
import ec.edu.epn.javlySgr.reservation.Room;
import ec.edu.epn.javlySgr.reservation.RoomStatus;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ServiceProcessorTest {
    private ReservationProcessor reservationProcessor = null;

    @Before
    public void setUp() {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, "Simple room", 70.00, RoomStatus.AVAILABLE));
        this.reservationProcessor = new ReservationProcessor(rooms);
        this.reservationProcessor.makeReservation(
            new Client("Juan", "Jaramillo", "example@example.com","123"),
            "22/10/2021",
            "25/10/2021",
            1
        );
    }

    @Test
    public void given_reservation_id_when_reservation_exists_then_ok () {
        ServiceProcessor serviceProcessor = new ServiceProcessor(this.reservationProcessor);
        boolean result =  serviceProcessor.addService(1, ServiceType.ROOM_SERVICE, "N/A");
        assertTrue(result);
    }

    @Test
    public void given_reservation_id_when_reservation_not_exists_then_error () {
        ServiceProcessor serviceProcessor = new ServiceProcessor(this.reservationProcessor);
        boolean result =  serviceProcessor.addService(50, ServiceType.ROOM_SERVICE, "N/A");
        assertFalse(result);
    }

    @Test
    public void given_reservation_id_when_reservation_exists_then_reservation_increase_price () {
        Reservation reservation =  this.reservationProcessor.getReservationByCode(1);
        double prevPrice = reservation.getPrice();
        ServiceProcessor serviceProcessor = new ServiceProcessor(this.reservationProcessor);
        serviceProcessor.addService(1, ServiceType.ROOM_SERVICE, "N/A");
        double newPrice = reservation.getPrice();
        assertEquals(prevPrice + ServiceType.ROOM_SERVICE.getPrice(), newPrice, 0.001);
    }
}