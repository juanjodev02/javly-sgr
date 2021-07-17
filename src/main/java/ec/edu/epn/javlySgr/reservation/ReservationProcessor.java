package ec.edu.epn.javlySgr.reservation;

import ec.edu.epn.javlySgr.client.Client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public boolean makeReservation(Client client, String checkInDate, String checkOutDate, int roomNumber) {
        try {
            Date parsedCheckInDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkInDate);
            Date parsedCheckOutDate = new SimpleDateFormat("dd/MM/yyy").parse(checkOutDate);
            Room room = this.getRoomById(roomNumber);
            if( room == null || room.getRoomStatus() == RoomStatus.RESERVED || room.getRoomStatus() == RoomStatus.MAINTENANCE) {
                return false;
            }

            boolean roomAvailability = checkRoomAvailability(parsedCheckInDate, parsedCheckOutDate, roomNumber);

            if(!roomAvailability) {
                return false;
            }

            Reservation newReservation = new Reservation(
                    this.reservations.size() + 1,
                    client,
                    room,
                    parsedCheckInDate,
                    parsedCheckOutDate
            );

            newReservation.getRoom().setRoomStatus(RoomStatus.RESERVED);

            this.reservations.add(newReservation);

            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkRoomAvailability(Date checkInDate, Date checkOutDate, int roomNumber) {
        boolean availability = false;
        boolean checkInDateIsBeforeSystemDate = new Date().after(checkInDate);
        boolean checkOutDateIsBeforeSystemDate = new Date().after(checkInDate);
        if (this.reservations.size() == 0) {
            return !checkInDateIsBeforeSystemDate && !checkOutDateIsBeforeSystemDate;
        }

        for(Reservation reservation : this.reservations) {
            boolean isCanceled = reservation.getReservationStatus() == ReservationStatus.CANCELED;
            int reservedRoomNumber = reservation.getRoom().getNumber();
            if(checkInDateIsBeforeSystemDate && checkOutDateIsBeforeSystemDate) {
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

    public Reservation getReservationByCode(int code) {
        Reservation reservation = null;
        for(Reservation currentReservation : this.reservations) {
            if(currentReservation.getCode() == code) {
                reservation = currentReservation;
            }
        }
        return reservation;
    }
}
