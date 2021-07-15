package ec.edu.epn.javlySgr.registration;

import ec.edu.epn.javlySgr.employee.Employee;
import ec.edu.epn.javlySgr.reservation.Reservation;

public class Registration {
    private String hour;
    private Employee employee;
    private Reservation reservation;
    public Registration(String hour, Employee employee, Reservation reservation) {
        this.hour = hour;
        this.employee = employee;
        this.reservation = reservation;
    }

    public String getHour() {
        return hour;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Reservation getReservation() {
        return reservation;
    }
}
