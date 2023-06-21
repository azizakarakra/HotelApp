package edu.bzu.hotelproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void btnLogin(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void ToSignup(View view) {
        Intent intent = new Intent(this,SignupActivity.class);
        startActivity(intent);
    }

    public void toContact1(View view) {

    }

    public void About(View view) {
    }

    public void toHotel(View view) {
        Intent intent = new Intent(this,HotelProfile.class);
        startActivity(intent);
    }
}