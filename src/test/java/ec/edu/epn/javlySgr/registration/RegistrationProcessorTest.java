package ec.edu.epn.javlySgr.registration;

import ec.edu.epn.javlySgr.client.Client;
import ec.edu.epn.javlySgr.employee.Employee;
import ec.edu.epn.javlySgr.reservation.Reservation;
import ec.edu.epn.javlySgr.reservation.ReservationProcessor;
import ec.edu.epn.javlySgr.reservation.Room;
import ec.edu.epn.javlySgr.reservation.RoomStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RegistrationProcessorTest {
    private RegistrationProcessor registrationProcessor = null;
    private ReservationProcessor reservationProcessor = null;
    private Reservation reservation = null;
    private Employee employee = null;
    private Client client = null;

    @Before
    public void setUp() throws ParseException {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, "Simple room", 70.00, RoomStatus.AVAILABLE));
        rooms.add(new Room(2, "Double room", 100.00, RoomStatus.RESERVED));
        rooms.add(new Room(3, "Simple room", 70.00, RoomStatus.MAINTENANCE));
        rooms.add(new Room(4, "Simple room", 70.00, RoomStatus.AVAILABLE));
        this.client = new Client("Juan", "Jaramillo", "juan.jaramillo02@epn.edu.ec", "secure-password");
        this.reservationProcessor = new ReservationProcessor(rooms);
        this.reservationProcessor.makeReservation(this.client,"29/10/2021", "1/11/2021", 1);
        this.reservationProcessor.makeReservation(this.client,"15/12/2021", "17/12/2021", 4);
        this.reservationProcessor.getReservations().forEach(x -> {
            System.out.println(x.getCode());
        });
        this.reservation = this.reservationProcessor.getReservationByCode(1);
        this.employee = new Employee("Lesly", "Tipanluiza", "Receptions", "secure-password", "lesly.tipanluiza");
        Date mockedDate = new SimpleDateFormat("dd/MM/yyyy").parse("29/10/2021");
        RegistrationProcessor plainRegistrationProcessor = new RegistrationProcessor(employee, this.reservationProcessor);
        this.registrationProcessor = Mockito.spy(plainRegistrationProcessor);
        Mockito.when(registrationProcessor.getSystemDate()).thenReturn(mockedDate);
    }

    @Test()
    public void given_reservation_code_when_reservation_exists_then_ok() {
        RegistrationStatus response = this.registrationProcessor.makeRegistration(this.reservation.getCode());
        Assert.assertEquals(RegistrationStatus.OK, response);
    }

    @Test()
    public void given_reservation_code_when_reservation_not_exists_then_error() {
        RegistrationStatus response = this.registrationProcessor.makeRegistration(this.reservation.getCode() + 1);
        Assert.assertEquals(RegistrationStatus.RESERVATION_NOT_FOUND, response);
    }

    @Test()
    public void given_reservation_code_when_date_is_before_date_system_then_error() {
        RegistrationProcessor customRegistrationProcessor = Mockito.spy(new RegistrationProcessor(this.employee, this.reservationProcessor));
        RegistrationStatus response = customRegistrationProcessor.makeRegistration(2);
        Assert.assertEquals(RegistrationStatus.REGISTRATION_BEFORE_CHECK_IN_DATE, response);
    }
}