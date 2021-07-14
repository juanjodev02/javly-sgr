package ec.edu.epn.javlySgr.reservation;

/**
 * COMPLETED: The reservation was completed successfully
 * IN_PROGRESS: The reservation is between check in date and check out date
 * WAITING: The reservation is waiting the check in date
 * CANCELED: The reservation was canceled by client or the payment was not made before check in date
 */
public enum ReservationStatus {
    WATING,
    COMPLETED,
    IN_PROGRESS,
    CANCELED
}
