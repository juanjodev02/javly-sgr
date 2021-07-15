package ec.edu.epn.javlySgr.registration;

import ec.edu.epn.javlySgr.employee.Employee;
import ec.edu.epn.javlySgr.reservation.Reservation;
import ec.edu.epn.javlySgr.reservation.ReservationProcessor;

import java.util.ArrayList;
import java.util.Date;

public class RegistrationProcessor {
    private final Employee employee;
    private final ReservationProcessor reservationProcessor;
    private final Date systemDate;
    private final ArrayList<Registration> registrations = new ArrayList<>();

    public RegistrationProcessor(Employee employee, ReservationProcessor reservationProcessor) {
        this.employee = employee;
        this.reservationProcessor = reservationProcessor;
        this.systemDate = new Date();
    }

    public RegistrationStatus makeRegistration(int reservationCode) {
        Reservation reservation = this.reservationProcessor.getReservationByCode(reservationCode);
        if (reservation == null) {
            return RegistrationStatus.RESERVATION_NOT_FOUND;
        }

        if(reservation.getCheckInDate().before(this.systemDate)) {
            return RegistrationStatus.REGISTRATION_BEFORE_CHECK_IN_DATE;
        }

        if(reservation.getCheckInDate().after(this.systemDate)) {
            return RegistrationStatus.REGISTRATION_AFTER_CHECK_IN_DATE;
        }

        Registration registration = new Registration(new Date().getTime()+"", this.employee, reservation);
        this.registrations.add(registration);
        return RegistrationStatus.OK;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Date getSystemDate() {
        return systemDate;
    }

    public ArrayList<Registration> getRegistrations() {
        return registrations;
    }
}
