package ec.edu.epn.javlySgr.reservation;

public class Room {
    private int number;
    private String description;
    private double price;
    private RoomStatus roomStatus;

    public Room(int number, String description, double price, RoomStatus roomStatus) {
        this.number = number;
        this.description = description;
        this.price = price;
        this.roomStatus = roomStatus;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }
}
