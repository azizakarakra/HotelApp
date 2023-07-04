package edu.bzu.hotelproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.Touch;
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

import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class User_Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

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
//        END CODE NAVBAR

        ImageView editName = (ImageView) findViewById(R.id.editName);
        ImageView editEmail = (ImageView) findViewById(R.id.editEmail);
        ImageView editPassword = (ImageView) findViewById(R.id.editPassword);
        ImageView editPhone = (ImageView) findViewById(R.id.editPhone);
        ImageView editDateOfBirth = (ImageView) findViewById(R.id.editDateOfBirth);

        ImageView saveName = (ImageView) findViewById(R.id.saveName);
        ImageView saveEmail = (ImageView) findViewById(R.id.saveEmail);
        ImageView savePassword = (ImageView) findViewById(R.id.savePassword);
        ImageView savePhone = (ImageView) findViewById(R.id.savePhone);
        ImageView saveDateOfBirth = (ImageView) findViewById(R.id.saveDateOfBirth);

        TextView userName = (TextView) findViewById(R.id.userName);
        TextView emailText = (TextView) findViewById(R.id.emailText);
        TextView passwordText = (TextView) findViewById(R.id.passwordText);
        TextView phoneText = (TextView) findViewById(R.id.phoneText);
        TextView dateOfBirthText = (TextView) findViewById(R.id.dateOfBirthText);

        EditText editUserNameText = (EditText) findViewById(R.id.editUserNameText);
        EditText editEmailText = (EditText) findViewById(R.id.editEmailText);
        EditText editPasswordText = (EditText) findViewById(R.id.editPasswordText);
        EditText editPhoneText = (EditText) findViewById(R.id.editPhoneText);

        Spinner day_spinner = findViewById(R.id.day_spinner);
        Spinner month_spinner = findViewById(R.id.month_spinner);
        Spinner year_spinner = findViewById(R.id.year_spinner);

        LinearLayout editEmailLayout = (LinearLayout) findViewById(R.id.editEmailLayout);
        LinearLayout editPasswordLayout = (LinearLayout) findViewById(R.id.editPasswordLayout);
        LinearLayout editPhoneLayout = (LinearLayout) findViewById(R.id.editPhoneLayout);
        LinearLayout editDateOfBirthLayout = (LinearLayout) findViewById(R.id.editDateOfBirthLayout);

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
                userName.setText(name);
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
                String email = editEmailText.getText().toString();
                editEmailLayout.setVisibility(View.GONE);
                emailText.setText(email);
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
                passwordText.setText(password);
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
                phoneText.setText(phone);
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

                dateOfBirthText.setText(dateOfBirth);
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
                startActivity(intent);
                finish();
                break;

            case R.id.nav_account:
                Toast.makeText(this, "account!", Toast.LENGTH_SHORT).show();
                intent = new Intent(User_Profile.this, User_Profile.class);
                startActivity(intent);
                finish();
                break;

            case R.id.nav_logout:
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                intent = new Intent(User_Profile.this, LoginActivity.class);
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