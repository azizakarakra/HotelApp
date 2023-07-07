package edu.bzu.hotelproject;

public interface UpdateUserCallback {
    void onUpdateSuccess(String message);
    void onUpdateError(String errorMessage);
}
