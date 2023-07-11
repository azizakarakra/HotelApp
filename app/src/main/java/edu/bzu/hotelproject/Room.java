package edu.bzu.hotelproject;

public class Room {

    private int roomID;
    private String roomName;
    private int roomSize;
    private boolean isBooked;
    private int floorNumber;
    private double roomPrice;
    private int numberOfBeds;

    public Room(int roomID, String roomName, int size, int floorNumber, double price, int numberOfBeds) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomSize = size;
        this.floorNumber = floorNumber;
        this.roomPrice = price;
        this.numberOfBeds = numberOfBeds;
        this.isBooked = false;

    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
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
