package edu.bzu.hotelproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    public static final String SAVEEMAIL = "SAVEEMAIL";
    public static final String SAVEPASS = "SAVEPASS";
    public static final String EMAIL = "EMAIL";
    public static final String PASS = "PASS";
    public static final String USERNAME = "USERNAME";
    public static final String PHONE = "PHONE";
    public static final String DATEOFBIRTH = "DATEOFBIRTH";
    public static final String FLAG = "FLAG";
    private boolean flag = false;
    private EditText EmailAddress;
    private EditText edtPassword;
    private CheckBox chk;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupViews();
        setupSharedPrefs();
        checkPrefs();

        Button login = (Button) findViewById(R.id.login);
        Button SignUpButton = (Button) findViewById(R.id.SignUpButton);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.signinProgressBar);
        progressBar.setVisibility(View.GONE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = EmailAddress.getText().toString();
                String password = edtPassword.getText().toString();

                if(chk.isChecked()){
                    if(!flag) {
                        editor.putString(SAVEEMAIL, email);
                        editor.putString(SAVEPASS, password);
                        editor.putBoolean(FLAG, true);
                        editor.commit();
                    }
                }

                progressBar.setProgress(50);
                progressBar.setVisibility(View.VISIBLE);

                String url = Constants.URL_LOGIN+ "?email=" + email + "&password=" + password;
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                        url,null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                try {
                                    JSONObject jsonObject = response.getJSONObject(0);
                                    editor.putString(EMAIL, email);
                                    editor.putString(PASS, password);
                                    editor.putString(USERNAME, jsonObject.getString("username"));
                                    editor.putString(PHONE, jsonObject.getString("phone"));
                                    editor.putString(DATEOFBIRTH, jsonObject.getString("dateofbirth"));
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), "Login was successful", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(LoginActivity.this, HotelProfile.class);
                                    intent.putExtra("email", email);
                                    intent.putExtra("username", jsonObject.getString("username"));
                                    startActivity(intent);
                                    finish();
                                }catch(JSONException exception){
                                    Log.d("Error", exception.toString());
                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, error.toString(),
                                        Toast.LENGTH_SHORT).show();
                                Log.d("Error_json", error.toString());
                            }
                        });
                requestQueue.add(jsonArrayRequest);
            }
        });

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void checkPrefs() {
        flag = prefs.getBoolean(FLAG, false);

        if(flag){
            String email = prefs.getString(SAVEEMAIL, "");
            String password = prefs.getString(SAVEPASS, "");
            EmailAddress.setText(email);
            edtPassword.setText(password);
            chk.setChecked(true);
        }
    }

    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void setupViews() {
        EmailAddress = findViewById(R.id.EmailAddress);
        edtPassword = findViewById(R.id.editTextPass);
        chk = findViewById(R.id.checkBox);
    }
}