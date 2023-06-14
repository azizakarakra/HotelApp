package edu.bzu.hotelproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class BookingOverviewActivity extends AppCompatActivity {

    private Button finish, cancel;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_overview);

        finish = (Button) findViewById(R.id.finish);
        cancel = (Button) findViewById(R.id.cancel);
        back = (ImageView) findViewById(R.id.back);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BookingOverviewActivity.this, "Your booking is confirmed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BookingOverviewActivity.this, HotelProfile.class);
                startActivity(intent);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingOverviewActivity.this, SelectRoomsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingOverviewActivity.this, SelectRoomsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}