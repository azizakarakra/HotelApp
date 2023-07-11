package edu.bzu.hotelproject;


public class Constants {

    private static final String ROOT_URL = "http://192.168.56.1/Hotel/v1/"; // Huthayfa IP:192.168.56.1

    public static final String URL_REGISTER = ROOT_URL+"registerUser.php";
    public static final String URL_LOGIN = ROOT_URL+"userLogin.php";
    public static final String URL_GET_USER = ROOT_URL+"getUserInfo.php?email=";
    public static final String URL_UPDATE_USER = ROOT_URL+"updateUserInfo.php";

    public static final String URL_GET_ROOMS = ROOT_URL+"getRooms.php";
    public static final String URL_GET_ROOM = ROOT_URL+"getRoom.php?id=";
    public static final String URL_DELETE_RESERVATION = ROOT_URL+"deleteReservation.php?id=";
    public static final String URL_GET_RESERVATION = ROOT_URL+"getReservation.php?email=";


    public static final String URL_BOOKING = ROOT_URL+"registerRooms.php";

}
