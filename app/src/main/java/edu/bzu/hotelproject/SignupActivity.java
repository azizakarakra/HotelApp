package edu.bzu.hotelproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    public static final String EMAIL = "EMAIL";
    public static final String PASS = "PASS";
    public static final String USERNAME = "USERNAME";
    public static final String PHONE = "PHONE";
    public static final String DATEOFBIRTH = "DATEOFBIRTH";

    ProgressBar progressBar;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setupSharedPrefs();

        EditText userName = (EditText) findViewById(R.id.userName);
        EditText email = (EditText) findViewById(R.id.email);
        EditText phone = (EditText) findViewById(R.id.phone);
        EditText dateOfBirth = (EditText) findViewById(R.id.dateOfBirth);
        EditText password = (EditText) findViewById(R.id.password);

        Button signUp = (Button) findViewById(R.id.signUp);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue requestQueue = Volley.newRequestQueue(SignupActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_REGISTER,
                        response -> {
                            progressBar.setProgress(50);
                            progressBar.setVisibility(View.VISIBLE);

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                editor.putString(EMAIL, email.getText().toString());
                                editor.putString(PASS, password.getText().toString());
                                editor.putString(USERNAME, userName.getText().toString());
                                editor.putString(PHONE, phone.getText().toString());
                                editor.putString(DATEOFBIRTH, dateOfBirth.getText().toString());
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(SignupActivity.this, HotelProfile.class);
                                intent.putExtra("email", email.getText().toString());
                                intent.putExtra("username", userName.getText().toString());
                                startActivity(intent);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        },
                        error -> {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                            System.out.println(error.getMessage());
                        }) {

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        JSONObject jsonBody = new JSONObject();
                        try {
                            jsonBody.put("username", userName.getText().toString());
                            jsonBody.put("email", email.getText().toString());
                            jsonBody.put("phone", phone.getText().toString());
                            jsonBody.put("dateofbirth", dateOfBirth.getText().toString());
                            jsonBody.put("password", password.getText().toString());
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

    }
    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
}