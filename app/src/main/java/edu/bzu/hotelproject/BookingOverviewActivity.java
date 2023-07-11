package edu.bzu.hotelproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class BookingOverviewActivity extends AppCompatActivity {

    private Button finish, cancel;
    private ImageView back;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    Intent intent;
    String username;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_overview);

        intent = getIntent();
        username = intent.getStringExtra("username");
        email = intent.getStringExtra("email");


        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        TextView selectedRoom = (TextView) findViewById(R.id.textView29);
        TextView price = (TextView) findViewById(R.id.textView22);
        TextView startDate = (TextView) findViewById(R.id.textView27);
        TextView endDate = (TextView) findViewById(R.id.textView28);

        finish = (Button) findViewById(R.id.finish);
        cancel = (Button) findViewById(R.id.cancel);
        back = (ImageView) findViewById(R.id.back);

        String roomName = prefs.getString("selectedRoom","not selected");
        float roomPrice = prefs.getFloat("selectedRoomPrice",(float) 0.0);
        String start = prefs.getString("startDate" , "not selected");
        String end = prefs.getString("endDate" , "not selected");
        int roomID = prefs.getInt("selectedRoomID" , -1);
        String userEmail = prefs.getString("EMAIL","no email");
        System.out.println("roomName: "+roomName+", roomPrice: "+roomPrice+", startDate: "+start+", endDate: "+end+", roomID: "+roomID+", userEmail: "+userEmail);

        selectedRoom.setText(roomName);
        price.setText(Float.toString(roomPrice));
        startDate.setText(start);
        endDate.setText(end);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(BookingOverviewActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_BOOKING,
                        response -> {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if(!jsonObject.getBoolean("error")){
                                    editor.putInt("selectedRoomID",-1);
                                    editor.putString("selectedRoom", "not selected");
                                    editor.putFloat("selectedRoomPrice", (float) 0.0);
                                    editor.putString("startDate","not selected");
                                    editor.putString("endDate","not selected");
                                    Toast.makeText(BookingOverviewActivity.this, "Your booking is confirmed", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(BookingOverviewActivity.this, HotelProfile.class);
                                    intent.putExtra("username", username);
                                    intent.putExtra("email", email);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        },
                        error -> {

                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                            System.out.println(error.getMessage());
                        }) {

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        JSONObject jsonBody = new JSONObject();
                        try {
                            jsonBody.put("roomId", roomID);
                            jsonBody.put("userEmail", userEmail);
                            jsonBody.put("start", start);
                            jsonBody.put("end", end);
                            jsonBody.put("price", roomPrice);
                            jsonBody.put("roomName", roomName);
                            // Add more user information if needed
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return jsonBody.toString().getBytes();
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=UTF-8";
                    }

                };

                requestQueue.add(stringRequest);

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingOverviewActivity.this, SelectRoomsActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingOverviewActivity.this, SelectRoomsActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }
        });
    }
}