package edu.bzu.hotelproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.Touch;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User_Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private DrawerLayout drawerLayout;

    ImageView editName ;
    ImageView editEmail ;
    ImageView editPassword;
    ImageView editPhone ;
    ImageView editDateOfBirth ;

    ImageView saveName;
    ImageView saveEmail;
    ImageView savePassword;
    ImageView savePhone;
    ImageView saveDateOfBirth;

    TextView userName;
    TextView emailText;
    TextView passwordText;
    TextView phoneText;
    TextView dateOfBirthText;

    EditText editUserNameText;
    EditText editEmailText;
    EditText editPasswordText ;
    EditText editPhoneText ;

    Spinner day_spinner ;
    Spinner month_spinner ;
    Spinner year_spinner ;

    LinearLayout editEmailLayout ;
    LinearLayout editPasswordLayout;
    LinearLayout editPhoneLayout;
    LinearLayout editDateOfBirthLayout ;

    Intent intent;
    String email;



    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public static final String EMAIL = "EMAIL";
    public static final String PASS = "PASS";
    public static final String USERNAME = "USERNAME";
    public static final String PHONE = "PHONE";
    public static final String DATEOFBIRTH = "DATEOFBIRTH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        intent = getIntent();
        email = intent.getStringExtra("email");

        setupSharedPrefs();

//        START CODE NAVBAR
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set custom title text
        getSupportActionBar().setTitle("Account");
        // Change the color of the Toolbar title
        toolbar.setTitleTextColor(getResources().getColor(R.color.orange_color_2));

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);

        // Set the color of the toggle icon
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.orange_color_2));

        toggle.setToolbarNavigationClickListener(v -> onBackPressed());
        toggle.setDrawerSlideAnimationEnabled(true);
        toggle.syncState();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        View headerView = navigationView.getHeaderView(0);
        TextView navUserName = headerView.findViewById(R.id.userName);


//        END CODE NAVBAR

        editName = (ImageView) findViewById(R.id.editName);
        editEmail = (ImageView) findViewById(R.id.editEmail);
        editPassword = (ImageView) findViewById(R.id.editPassword);
        editPhone = (ImageView) findViewById(R.id.editPhone);
        editDateOfBirth = (ImageView) findViewById(R.id.editDateOfBirth);

        saveName = (ImageView) findViewById(R.id.saveName);
        saveEmail = (ImageView) findViewById(R.id.saveEmail);
        savePassword = (ImageView) findViewById(R.id.savePassword);
        savePhone = (ImageView) findViewById(R.id.savePhone);
        saveDateOfBirth = (ImageView) findViewById(R.id.saveDateOfBirth);

        userName = (TextView) findViewById(R.id.userName);
        emailText = (TextView) findViewById(R.id.emailText);
        passwordText = (TextView) findViewById(R.id.passwordText);
        phoneText = (TextView) findViewById(R.id.phoneText);
        dateOfBirthText = (TextView) findViewById(R.id.dateOfBirthText);

        editUserNameText = (EditText) findViewById(R.id.editUserNameText);
        editEmailText = (EditText) findViewById(R.id.editEmailText);
        editPasswordText = (EditText) findViewById(R.id.editPasswordText);
        editPhoneText = (EditText) findViewById(R.id.editPhoneText);

        day_spinner = findViewById(R.id.day_spinner);
        month_spinner = findViewById(R.id.month_spinner);
        year_spinner = findViewById(R.id.year_spinner);

        editEmailLayout = (LinearLayout) findViewById(R.id.editEmailLayout);
        editPasswordLayout = (LinearLayout) findViewById(R.id.editPasswordLayout);
        editPhoneLayout = (LinearLayout) findViewById(R.id.editPhoneLayout);
        editDateOfBirthLayout = (LinearLayout) findViewById(R.id.editDateOfBirthLayout);


        String url = Constants.URL_GET_USER + email;
        RequestQueue requestQueue = Volley.newRequestQueue(User_Profile.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                userName.setText(jsonObject.getString("username"));
                                emailText.setText(jsonObject.getString("email"));
                                passwordText.setText(jsonObject.getString("password"));
                                phoneText.setText(jsonObject.getString("phone"));
                                dateOfBirthText.setText(jsonObject.getString("dateofbirth"));
                                navUserName.setText(jsonObject.getString("username"));
                            }catch(JSONException exception){
                                Log.d("Error", exception.toString());
                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(User_Profile.this, error.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("Error_json", error.toString());
                    }
        });
        requestQueue.add(jsonArrayRequest);

        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userName.getText().toString();
                userName.setVisibility(View.GONE);
                editName.setVisibility(View.GONE);
                editUserNameText.setText(name);
                editUserNameText.setVisibility(View.VISIBLE);
                saveName.setVisibility(View.VISIBLE);
            }
        });

        saveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editUserNameText.getText().toString();
                editUserNameText.setVisibility(View.GONE);
                saveName.setVisibility(View.GONE);

                sendRequest(name, emailText.getText().toString(), emailText.getText().toString()
                        , phoneText.getText().toString(), dateOfBirthText.getText().toString()
                        , passwordText.getText().toString(), new UpdateUserCallback() {
                            @Override
                            public void onUpdateSuccess(String message) {
                                userName.setText(name);
                                TextView navUserName = headerView.findViewById(R.id.userName);
                                navUserName.setText(name);
                                editor.putString(USERNAME, name);
                            }

                            @Override
                            public void onUpdateError(String errorMessage) {

                            }
                        });

                userName.setVisibility(View.VISIBLE);
                editName.setVisibility(View.VISIBLE);
            }
        });

        editEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailText.getText().toString();
                emailText.setVisibility(View.GONE);
                editEmailText.setText(email);
                editEmailLayout.setVisibility(View.VISIBLE);
            }
        });

        saveEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = editEmailText.getText().toString();
                editEmailLayout.setVisibility(View.GONE);
                sendRequest(userName.getText().toString(),emailText.getText().toString(),mail
                        ,phoneText.getText().toString(),dateOfBirthText.getText().toString()
                        ,passwordText.getText().toString(), new UpdateUserCallback() {
                            @Override
                            public void onUpdateSuccess(String message) {
                                emailText.setText(mail);
                                email = mail;
                                editor.putString(EMAIL, mail);
                            }

                            @Override
                            public void onUpdateError(String errorMessage) {

                            }
                        });

                emailText.setVisibility(View.VISIBLE);
            }
        });

        editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = passwordText.getText().toString();
                passwordText.setVisibility(View.GONE);
                editPasswordText.setText(password);
                editPasswordLayout.setVisibility(View.VISIBLE);
            }
        });

        savePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = editPasswordText.getText().toString();
                editPasswordLayout.setVisibility(View.GONE);

                sendRequest(userName.getText().toString(),emailText.getText().toString()
                        ,emailText.getText().toString(),phoneText.getText().toString()
                        ,dateOfBirthText.getText().toString(),password, new UpdateUserCallback() {
                            @Override
                            public void onUpdateSuccess(String message) {
                                passwordText.setText(password);
                                editor.putString(PASS, password);
                            }

                            @Override
                            public void onUpdateError(String errorMessage) {

                            }
                        });

                passwordText.setVisibility(View.VISIBLE);
            }
        });

        editPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phoneText.getText().toString();
                phoneText.setVisibility(View.GONE);
                editPhoneText.setText(phone);
                editPhoneLayout.setVisibility(View.VISIBLE);
            }
        });

        savePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = editPhoneText.getText().toString();
                editPhoneLayout.setVisibility(View.GONE);
                sendRequest(userName.getText().toString(),emailText.getText().toString()
                        ,emailText.getText().toString(),phone
                        ,dateOfBirthText.getText().toString(),passwordText.getText().toString(), new UpdateUserCallback() {
                            @Override
                            public void onUpdateSuccess(String message) {
                                phoneText.setText(phone);
                                editor.putString(PHONE, phone);
                            }

                            @Override
                            public void onUpdateError(String errorMessage) {

                            }
                        });

                phoneText.setVisibility(View.VISIBLE);
            }
        });

        editDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateOfBirth = dateOfBirthText.getText().toString();
                dateOfBirthText.setVisibility(View.GONE);

                String[] token = dateOfBirth.split("/");

                day_spinner.setSelection(Integer.parseInt(token[0])-1);
                month_spinner.setSelection(getMonthIndex(token[1]));
                year_spinner.setSelection(Integer.parseInt(token[2])-1950);

                editDateOfBirthLayout.setVisibility(View.VISIBLE);
            }
        });

        saveDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dayOfBirth = day_spinner.getSelectedItem().toString();
                String monthOfBirth = month_spinner.getSelectedItem().toString();
                String yearOfBirth = year_spinner.getSelectedItem().toString();
                String dateOfBirth = dayOfBirth+"/"+monthOfBirth+"/"+yearOfBirth;
                editDateOfBirthLayout.setVisibility(View.GONE);
                dateOfBirthText.setVisibility(View.VISIBLE);
                sendRequest(userName.getText().toString(),emailText.getText().toString()
                        ,emailText.getText().toString(),phoneText.getText().toString()
                        ,dateOfBirth,passwordText.getText().toString(), new UpdateUserCallback() {
                            @Override
                            public void onUpdateSuccess(String message) {
                                dateOfBirthText.setText(dateOfBirth);
                                editor.putString(DATEOFBIRTH, dateOfBirth);
                            }

                            @Override
                            public void onUpdateError(String errorMessage) {

                            }
                        });

                dateOfBirthText.setVisibility(View.VISIBLE);
            }
        });





        String[] days = new String[31];
        for (int i = 1; i<=31; i++){
            days[i-1] = Integer.toString(i);
        }

        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(User_Profile.this,
                android.R.layout.simple_spinner_dropdown_item, days);

        day_spinner.setAdapter(dayAdapter);

        String[] months;
        months = setMonthsCat();

        ArrayAdapter<String> monthsAdapter = new ArrayAdapter<>(User_Profile.this,
                android.R.layout.simple_spinner_dropdown_item, months);

        month_spinner.setAdapter(monthsAdapter);

        String[] years = new String[61];
        for (int i = 1950; i<=2010; i++){
            years[i-1950] = Integer.toString(i);
        }

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(User_Profile.this,
                android.R.layout.simple_spinner_dropdown_item, years);

        year_spinner.setAdapter(yearAdapter);

    }

    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }



    void sendRequest(String name, String oldEmail, String newEmail, String phone, String dateBirth, String pass, UpdateUserCallback callback){
        RequestQueue requestQueue = Volley.newRequestQueue(User_Profile.this);
        StringRequest stringRequest = new StringRequest(Request.Method.PUT,
                Constants.URL_UPDATE_USER,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (!jsonObject.getBoolean("error")){
                            callback.onUpdateSuccess(jsonObject.getString("message"));
                        }else{
                            callback.onUpdateError(jsonObject.getString("message"));
                        }
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

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
                    jsonBody.put("username", name);
                    jsonBody.put("oldEmail", oldEmail);
                    jsonBody.put("newEmail", newEmail);
                    jsonBody.put("phone", phone);
                    jsonBody.put("dateofbirth", dateBirth);
                    jsonBody.put("password", pass);
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



    String[] setMonthsCat(){
        String[] months = new String[12];

        months[0] = "Jan";
        months[1] = "Feb";
        months[2] = "Mar";
        months[3] = "Apr";
        months[4] = "May";
        months[5] = "Jun";
        months[6] = "Jul";
        months[7] = "Aug";
        months[8] = "Sep";
        months[9] = "Oct";
        months[10] = "Nov";
        months[11] = "Dec";

        return months;
    }

    int getMonthIndex(String month){
        if(month.equals("Jan")) return 0;
        else if(month.equals("Feb")) return 1;
        else if(month.equals("Mar")) return 2;
        else if(month.equals("Apr")) return 3;
        else if(month.equals("May")) return 4;
        else if(month.equals("Jun")) return 5;
        else if(month.equals("Jul")) return 6;
        else if(month.equals("Aug")) return 7;
        else if(month.equals("Sep")) return 8;
        else if(month.equals("Oct")) return 9;
        else if(month.equals("Nov")) return 10;
        else if(month.equals("Dec")) return 11;
        return 0;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View viewPopupwindow;
        Button apply, cancel;
        PopupWindow popupWindow;

        switch (item.getItemId()) {
            case R.id.nav_home:
                Toast.makeText(this, "home!", Toast.LENGTH_SHORT).show();
                intent = new Intent(User_Profile.this, HotelProfile.class);
                intent.putExtra("email", email);
                intent.putExtra("username",userName.getText().toString() );
                startActivity(intent);
                finish();
                break;

            case R.id.nav_settings:
                Toast.makeText(this, "Settings!", Toast.LENGTH_SHORT).show();
                viewPopupwindow = layoutInflater.inflate(R.layout.app_settings_popup, null);

                // Find views within the popup layout
                Spinner spinnerLanguage = viewPopupwindow.findViewById(R.id.spinnerLanguage);
                Spinner spinnerNotifications = viewPopupwindow.findViewById(R.id.spinnerNotifications);
                Spinner spinnerAppearance = viewPopupwindow.findViewById(R.id.spinnerAppearance);
                TextView PrivacyPolicy = viewPopupwindow.findViewById(R.id.PrivacyPolicy);
                TextView TermsandConditions = viewPopupwindow.findViewById(R.id.TermsandConditions);
                apply = viewPopupwindow.findViewById(R.id.apply);
                cancel = viewPopupwindow.findViewById(R.id.cancel);

                popupWindow = new PopupWindow(
                        viewPopupwindow,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        true
                );

                String [] datalist1={"English","العربية"};
                String [] datalist2={"Enable","Disable"};
                String [] datalist3={"Light theme","Dark theme"};

                ArrayAdapter<String> ad = new ArrayAdapter<>(this,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,datalist1);
                spinnerLanguage.setAdapter(ad);
                ArrayAdapter<String> ad2 = new ArrayAdapter<>(this,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,datalist2);
                spinnerNotifications.setAdapter(ad2);
                ArrayAdapter<String> ad3 = new ArrayAdapter<>(this,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,datalist3);
                spinnerAppearance.setAdapter(ad3);


                popupWindow.showAtLocation(viewPopupwindow, Gravity.BOTTOM, 0, 0);
                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String selectedLanguage = spinnerLanguage.getSelectedItem().toString();
                        String selectedNotifications = spinnerNotifications.getSelectedItem().toString();
                        String selectedAppearance = spinnerAppearance.getSelectedItem().toString();

                        // Do something with the selected number
                        popupWindow.dismiss();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                break;


            case R.id.nav_about:
                Toast.makeText(this, "about!", Toast.LENGTH_SHORT).show();
                intent = new Intent(User_Profile.this, AdsActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("username",userName.getText().toString() );
                startActivity(intent);
                finish();
                break;

            case R.id.nav_account:
                Toast.makeText(this, "account!", Toast.LENGTH_SHORT).show();
                intent = new Intent(User_Profile.this, User_Profile.class);
                intent.putExtra("email", email);
                intent.putExtra("username",userName.getText().toString() );
                startActivity(intent);
                finish();
                break;

            case R.id.nav_booking:
                Toast.makeText(this, "booking!", Toast.LENGTH_SHORT).show();
                intent = new Intent(User_Profile.this, UserBookingActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("username",userName.getText().toString() );
                startActivity(intent);
                finish();
                break;

            case R.id.nav_logout:
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                intent = new Intent(User_Profile.this, LoginActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("username",userName.getText().toString() );
                startActivity(intent);
                finish();
                break;

            case R.id.nav_share:
                Toast.makeText(this, "Link copied!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_rate:
                Toast.makeText(this, "rate!", Toast.LENGTH_SHORT).show();
                viewPopupwindow = layoutInflater.inflate(R.layout.rate_popup, null);

                apply = viewPopupwindow.findViewById(R.id.apply);
                cancel = viewPopupwindow.findViewById(R.id.cancel);

                popupWindow = new PopupWindow(
                        viewPopupwindow,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        true
                );

                popupWindow.showAtLocation(viewPopupwindow, Gravity.BOTTOM, 0, 0);
                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Do something with the selected number
                        popupWindow.dismiss();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
