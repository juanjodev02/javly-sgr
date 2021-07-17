package ec.edu.epn.javlySgr.reservation;

import ec.edu.epn.javlySgr.client.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

@RunWith(value = Parameterized.class)
public class ReservationProcessorTest {
    private ReservationProcessor reservationProcessor = null;
    private final Client client;
    private final String checkInDate;
    private final String checkOutDate;
    private final int roomNumber;

    @Before
    public void setUp() {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, "Simple room", 70.00, RoomStatus.AVAILABLE));
        rooms.add(new Room(2, "Double room", 100.00, RoomStatus.RESERVED));
        rooms.add(new Room(3, "Simple room", 70.00, RoomStatus.MAINTENANCE));
        rooms.add(new Room(4, "Simple room", 70.00, RoomStatus.AVAILABLE));
        this.reservationProcessor = new ReservationProcessor(rooms);
    }

    @Parameterized.Parameters
    public static Iterable<Object[]>parameters() {
        List<Object[]> objects = new ArrayList<>();
        Client client = new Client("Juan", "Jaramillo", "example@example.com", "secure_password");
        objects.add(new Object[]{client, "22/10/2021", "27/10/2021", 2 });
        objects.add(new Object[]{client, "22/10/2021", "27/10/2021", 3 });
        objects.add(new Object[]{client, "22/10/2021", "27/10/2021", 100 });
        objects.add(new Object[]{client, "22/10/2010", "27/10/2010", 4});
        return objects;
    }

    public ReservationProcessorTest(Client client, String checkInDate, String checkOutDate, int roomNumber) {
        this.client = client;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomNumber = roomNumber;
    }

    @Test()
    public void given_error_parameters_when_make_reservation_error() {
        boolean result =  this.reservationProcessor.makeReservation(this.client, this.checkInDate, this.checkOutDate, this.roomNumber);
        Assert.assertFalse(result);
    }

    @Test()
    public void given_room_client_and_dates_when_room_is_available_then_ok() {
        boolean result =  this.reservationProcessor.makeReservation(this.client, "22/10/2021", "27/10/2021", 1);
        Assert.assertTrue(result);
    }
}