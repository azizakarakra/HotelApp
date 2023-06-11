package edu.bzu.hotelproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SelectRoomsActivity extends AppCompatActivity {

    private ImageView menu, appLogo;
    private Button room1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_rooms);

        appLogo = (ImageView) findViewById(R.id.appLogo);
        appLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectRoomsActivity.this, HotelProfile.class);
                startActivity(intent);
                finish();
            }
        });
        room1 = (Button) findViewById(R.id.room1);
        room1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectRoomsActivity.this, FillInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void showGallery(View view) {

    }
}