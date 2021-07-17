package ec.edu.epn.javlySgr.reservation;

import ec.edu.epn.javlySgr.client.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ReservationProcessorTest {
    private ReservationProcessor reservationProcessor = null;
    private Client client = null;

    @Before
    public void setUp() throws Exception {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, "Simple room", 70.00, RoomStatus.AVAILABLE));
        rooms.add(new Room(2, "Double room", 100.00, RoomStatus.RESERVED));
        rooms.add(new Room(3, "Simple room", 70.00, RoomStatus.MAINTENANCE));
        rooms.add(new Room(4, "Simple room", 70.00, RoomStatus.AVAILABLE));
        this.reservationProcessor = new ReservationProcessor(rooms);

        this.client = new Client("Juan", "Jaramillo", "example@example.com", "secure_password");
    }

    @Test()
    public void given_room_client_and_dates_when_room_is_available_then_ok() {
        boolean result =  this.reservationProcessor.makeReservation(this.client, "22/10/2021", "27/10/2021", 1);
        Assert.assertTrue(result);
    }

    @Test()
    public void given_room_client_and_dates_when_room_is_reserved_then_error() {
        boolean result =  this.reservationProcessor.makeReservation(this.client, "22/10/2021", "27/10/2021", 2);
        Assert.assertFalse(result);
    }

    @Test()
    public void given_room_client_and_dates_when_room_is_maintenance_then_error() {
        boolean result =  this.reservationProcessor.makeReservation(this.client, "22/10/2021", "27/10/2021", 3);
        Assert.assertFalse(result);
    }

    @Test()
    public void given_room_client_and_dates_when_room_not_exist_then_error() {
        boolean result =  this.reservationProcessor.makeReservation(this.client, "22/10/2021", "27/10/2021", 100);
        Assert.assertFalse(result);
    }

    @Test()
    public void given_room_client_and_dates_when_checkInDate_is_before_system_date_then_error() {
        boolean result =  this.reservationProcessor.makeReservation(this.client, "22/10/2010", "27/10/2010", 4);
        Assert.assertFalse(result);
    }
}