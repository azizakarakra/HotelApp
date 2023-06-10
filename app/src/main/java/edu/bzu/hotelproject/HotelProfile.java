package edu.bzu.hotelproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HotelProfile extends AppCompatActivity {

    ImageView menu, appLogo;
    Button reservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_profile);

        menu = (ImageView) findViewById(R.id.menu);
        appLogo = (ImageView) findViewById(R.id.appLogo);
        reservation = (Button)findViewById(R.id.reservation);

        appLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelProfile.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelProfile.this, User_Profile.class);
                startActivity(intent);
                finish();
            }
        });

        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelProfile.this, ReservationActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}