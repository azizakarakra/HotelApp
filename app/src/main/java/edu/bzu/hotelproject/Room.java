package edu.bzu.hotelproject;

public class Room {

    private String roomName;
    private int roomSize;
    private boolean isBooked;
    private int floorNumber;
    private double roomPrice;
    private int numberOfBeds;

    public Room(String roomName, int size, int floorNumber, double price, int numberOfBeds) {
        this.roomName = roomName;
        this.roomSize = size;
        this.floorNumber = floorNumber;
        this.roomPrice = price;
        this.numberOfBeds = numberOfBeds;
        this.isBooked = false;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getSize() {
        return roomSize;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public double getPrice() {
        return roomPrice;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }
}
