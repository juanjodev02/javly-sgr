package ec.edu.epn.javlySgr.reservation;

import ec.edu.epn.javlySgr.client.Client;
import ec.edu.epn.javlySgr.payment.PaymentStatus;

import java.util.Date;

public class Reservation {
    private final int code;
    private final Client client;
    private final Room room;
    private final Date checkInDate;
    private final Date checkOutDate;
    private final int datesDiff;
    private final double price;
    private PaymentStatus paymentStatus;
    private ReservationStatus reservationStatus;

    public Reservation(int code, Client client, Room room, Date checkIn, Date checkOut) {
        this.code = code;
        this.client = client;
        this.room = room;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
        this.datesDiff = calculateDatesDiff();
        this.price = this.calculatePrice();
        this.paymentStatus = PaymentStatus.PENDING;
        this.reservationStatus = ReservationStatus.WATING;
    }

    private double calculatePrice() {
        return this.room.getPrice() * this.datesDiff;
    }

    private int calculateDatesDiff() {
        long checkInTime = this.checkInDate.getTime();
        long checkOutTime = this.checkOutDate.getTime();
        long timeDiff;
        if(checkInTime > checkOutTime) {
            timeDiff = checkInTime - checkOutTime;
        } else {
            timeDiff = checkOutTime - checkInTime;
        }
        return (int) (timeDiff / (1000 * 60 * 60* 24));
    }

    public int getCode() {
        return code;
    }

    public Client getClient() {
        return client;
    }

    public Room getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public int getDatesDiff() {
        return datesDiff;
    }

    public double getPrice() {
        return price;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
