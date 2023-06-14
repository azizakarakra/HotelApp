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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class SelectRoomsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private DrawerLayout drawerLayout;
    private Button room1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_rooms);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set custom title text
        getSupportActionBar().setTitle("Select Room");
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
                intent = new Intent(SelectRoomsActivity.this, HotelProfile.class);
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
                intent = new Intent(SelectRoomsActivity.this, AdsActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.nav_account:
                Toast.makeText(this, "account!", Toast.LENGTH_SHORT).show();
                intent = new Intent(SelectRoomsActivity.this, User_Profile.class);
                startActivity(intent);
                finish();
                break;

            case R.id.nav_logout:
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                intent = new Intent(SelectRoomsActivity.this, LoginActivity.class);
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