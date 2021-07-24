package ec.edu.epn.javlySgr.service;

import ec.edu.epn.javlySgr.reservation.Reservation;
import ec.edu.epn.javlySgr.reservation.ReservationProcessor;

public class ServiceProcessor {
    private ReservationProcessor reservationProcessor;

    public ServiceProcessor(ReservationProcessor reservationProcessor) {
        this.reservationProcessor = reservationProcessor;
    }

    public boolean addService(int reservationCode, ServiceType serviceType, String observation) {
        Reservation reservation = this.reservationProcessor.getReservationByCode(reservationCode);
        if (reservation == null) {
            return false;
        }
        reservation.addService(new Service(serviceType, observation));
        return true;
    }
}
