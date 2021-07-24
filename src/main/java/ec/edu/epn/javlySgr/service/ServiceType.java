package ec.edu.epn.javlySgr.service;

public enum ServiceType {
    ROOM_SERVICE(20.00),
    BREAKFAST(5.00),
    DINNER(5.00),
    TRANSPORTATION(10.00),
    LIFE_SECURE(30.00);

    private final double price;

    ServiceType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
