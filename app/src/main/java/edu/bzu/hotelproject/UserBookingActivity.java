package edu.bzu.hotelproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserBookingActivity extends AppCompatActivity {

    private ImageView back;
    private RecyclerView myRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_booking);

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserBookingActivity.this, HotelProfile.class);
                startActivity(intent);
                finish();
            }
        });

        myRecyclerView = findViewById(R.id.myRecyclerView);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Reservation> itemList = new ArrayList<>();

        // Check if itemList is not null
        if (itemList != null) {
            Toast.makeText(this, "Data retrieved successfully", Toast.LENGTH_SHORT).show();
            BookingAdapter adapter = new BookingAdapter(this, itemList);
            myRecyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No rooms available", Toast.LENGTH_SHORT).show();
        }

    }
}