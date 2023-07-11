package edu.bzu.hotelproject;

public class Reservation {
    private int roomId;
    private String roomName;
    private String startDate;
    private String endDate;
    private double price;
    private String userEmail;

    public Reservation(int roomId, String roomName, String startDate, String endDate, double price, String userEmail) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.userEmail = userEmail;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
