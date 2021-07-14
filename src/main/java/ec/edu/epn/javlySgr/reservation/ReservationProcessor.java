package ec.edu.epn.javlySgr.reservation;

import ec.edu.epn.javlySgr.client.Client;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class ReservationProcessor {
    private final ArrayList<Room> rooms = new ArrayList<>();
    private final ArrayList<Reservation> reservations = new ArrayList<>();

    public ReservationProcessor(ArrayList<Room> rooms) {
        this.rooms.addAll(rooms);
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public boolean makeReservation(Client client, Date checkInDate, Date checkOutDate, int roomNumber) {
        Room room = this.getRoomById(roomNumber);
        if( room == null) {
            return false;
        }

        boolean roomAvailability = checkRoomAvailability(checkInDate, checkOutDate, roomNumber);

        if(!roomAvailability) {
            return false;
        }

        Reservation newReservation = new Reservation(
                this.reservations.size() + 1,
                client,
                room,
                checkInDate,
                checkOutDate
        );

        this.reservations.add(newReservation);

        return true;
    }

    public boolean checkRoomAvailability(Date checkInDate, Date checkOutDate, int roomNumber) {
        boolean availability = false;

        if (this.reservations.size() == 0) return true;

        for(Reservation reservation : this.reservations) {
            boolean isCanceled = reservation.getReservationStatus() == ReservationStatus.CANCELED;
            int reservedRoomNumber = reservation.getRoom().getNumber();
            if(!isCanceled && reservedRoomNumber == roomNumber) {
                boolean isBetweenReservedDays = checkInDate.after(reservation.getCheckInDate()) && checkInDate.before(reservation.getCheckOutDate());
                if(!isBetweenReservedDays){
                    boolean dateOverlaps = checkOutDate.after(reservation.getCheckInDate()) && checkOutDate.before(reservation.getCheckOutDate());
                    if(!dateOverlaps) {
                        availability = true;
                    }
                }
            }
        }

        return availability;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public Room getRoomById(int roomNumber) {
        Room room = null;
        for(Room currentRoom : this.rooms) {
            if (currentRoom.getNumber() == roomNumber) {
                room = currentRoom;
            }
        }
        return room;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }
}
