package edu.bzu.hotelproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserBookingActivity extends AppCompatActivity {

    private ImageView back;
    private RecyclerView myRecyclerView;
    Intent intent;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_booking);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        intent = getIntent();
        email = intent.getStringExtra("email");

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

        //        _______________________________________________________________________________________________________

        List<Reservation> itemList = new ArrayList<>();
        myRecyclerView = findViewById(R.id.myRecyclerView);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        UpdateUserCallback callback = new UpdateUserCallback() {
            @Override
            public void onUpdateSuccess(String message) {

                // Check if itemList is not null
                if (itemList != null) {
                    Toast.makeText(UserBookingActivity.this, "Data retrieved successfully", Toast.LENGTH_SHORT).show();
                    BookingAdapter adapter = new BookingAdapter(UserBookingActivity.this, itemList);
                    myRecyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(UserBookingActivity.this, "No rooms available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onUpdateError(String errorMessage) {

            }
        };


        String url = Constants.URL_GET_RESERVATION + email;
        RequestQueue requestQueue = Volley.newRequestQueue(UserBookingActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //JSONObject jsonObject = response.getJSONObject(0);
                            JSONArray booksArray = response.getJSONArray("reservations");
                            for (int i = 0; i < booksArray.length(); i++) {
                                JSONObject roomObject = booksArray.getJSONObject(i);
                                int roomID = roomObject.getInt("roomId");
                                String roomName = roomObject.getString("roomName");
                                String userEmail = roomObject.getString("userEmail");
                                String start = roomObject.getString("start");
                                String end = roomObject.getString("end");
                                Double price = roomObject.getDouble("price");
                                Reservation r = new Reservation(roomID,roomName, start, end, price, userEmail);
                                itemList.add(r);
                            }
                            callback.onUpdateSuccess(response.getString("message"));

                        } catch (JSONException e) {
                            Log.d("Error", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserBookingActivity.this, error.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("Error_json", error.toString());
                    }
                });

        requestQueue.add(jsonObjectRequest);

//        _______________________________________________________________________________________________________

    }
}