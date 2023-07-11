package edu.bzu.hotelproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class ReservationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private DatePickerDialog datePickerDialogStart, datePickerDialogEnd;
    private Button checkInDatePickerButton, checkOutDatePickerButton, selectRoomsButton;

    private Button showPopup;

    Intent intent;
    String username;
    String email;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        intent = getIntent();
        username = intent.getStringExtra("username");
        email = intent.getStringExtra("email");

//        navbar start code:
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set custom title text
        getSupportActionBar().setTitle("Booking");
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
        TextView userName = headerView.findViewById(R.id.userName);
        userName.setText(username);

//        navbar end code

        initDatePickers();
        checkInDatePickerButton = findViewById(R.id.checkInDatePickerButton);
        checkInDatePickerButton.setText(getTodaysDate());
        checkOutDatePickerButton = findViewById(R.id.checkOutDatePickerButton);
        checkOutDatePickerButton.setText(getTodaysDate());

        checkInDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStartDatePicker(view);
                editor.putString("startDate", checkInDatePickerButton.getText().toString());
            }
        });
        checkOutDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEndDatePicker(view);
                editor.putString("endDate", checkOutDatePickerButton.getText().toString());
            }
        });

        showPopup = findViewById(R.id.showPopup);
        showPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View viewPopupwindow = layoutInflater.inflate(R.layout.popup_window, null);

                // Find views within the popup layout
                Button addFloorNumber = viewPopupwindow.findViewById(R.id.addFloorNumber);
                Button subFloorNumber = viewPopupwindow.findViewById(R.id.subFloorNumber);
                TextView floorNumber = viewPopupwindow.findViewById(R.id.floorNumber);
                Button addRoomsNumber = viewPopupwindow.findViewById(R.id.addRoomsNumber);
                Button subRoomsNumber = viewPopupwindow.findViewById(R.id.subRoomsNumber);
                TextView roomsNumber = viewPopupwindow.findViewById(R.id.roomsNumber);
                Button addAdultsNumber = viewPopupwindow.findViewById(R.id.addAdultsNumber);
                Button subAdultsNumber = viewPopupwindow.findViewById(R.id.subAdultsNumber);
                TextView adultsNumber = viewPopupwindow.findViewById(R.id.adultsNumber);
                Button addChildrenNumber = viewPopupwindow.findViewById(R.id.addChildrenNumber);
                Button subChildrenNumber = viewPopupwindow.findViewById(R.id.subChildrenNumber);
                TextView childrenNumber = viewPopupwindow.findViewById(R.id.childrenNumber);
                Button apply = viewPopupwindow.findViewById(R.id.apply);
                Button cancel = viewPopupwindow.findViewById(R.id.cancel);
                ConstraintLayout constraintLayout = viewPopupwindow.findViewById(R.id.root);

                addFloorNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int floorNum = Integer.parseInt(floorNumber.getText().toString());
                        if (floorNum < 10) {
                            floorNum++;
                            floorNumber.setText(String.valueOf(floorNum));
                        }
                    }
                });
                subFloorNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int floorNum = Integer.parseInt(floorNumber.getText().toString());
                        if (floorNum > 0) {
                            floorNum--;
                            floorNumber.setText(String.valueOf(floorNum));
                        }
                    }
                });

                addRoomsNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int roomNum = Integer.parseInt(roomsNumber.getText().toString());
                        if (roomNum < 30) {
                            roomNum++;
                            roomsNumber.setText(String.valueOf(roomNum));
                        }
                    }
                });

                subRoomsNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int roomNum = Integer.parseInt(roomsNumber.getText().toString());
                        if (roomNum > 0) {
                            roomNum--;
                            roomsNumber.setText(String.valueOf(roomNum));
                        }
                    }
                });

                addAdultsNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int n = Integer.parseInt(adultsNumber.getText().toString());
                        if (n < 30) {
                            n++;
                            adultsNumber.setText(String.valueOf(n));
                        }
                    }
                });

                subAdultsNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int n = Integer.parseInt(adultsNumber.getText().toString());
                        if (n > 0) {
                            n--;
                            adultsNumber.setText(String.valueOf(n));
                        }
                    }
                });

                addChildrenNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int n = Integer.parseInt(childrenNumber.getText().toString());
                        if (n < 30) {
                            n++;
                            childrenNumber.setText(String.valueOf(n));
                        }
                    }
                });

                subChildrenNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int n = Integer.parseInt(childrenNumber.getText().toString());
                        if (n > 0) {
                            n--;
                            childrenNumber.setText(String.valueOf(n));
                        }
                    }
                });

                PopupWindow popupWindow = new PopupWindow(viewPopupwindow,
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int selectedNumber = Integer.parseInt(floorNumber.getText().toString());
                        int selectedroomsNumber = Integer.parseInt(roomsNumber.getText().toString());
                        int selectedadultsNumber = Integer.parseInt(adultsNumber.getText().toString());
                        int selectedchildrenNumber = Integer.parseInt(childrenNumber.getText().toString());
                        // Do something with the selected number
                        showPopup.setText("floor "+selectedNumber+", "+selectedroomsNumber+" rooms, "+selectedadultsNumber+" adults, "+selectedchildrenNumber+" children");
                        popupWindow.dismiss();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
            }
        });

        selectRoomsButton = (Button) findViewById(R.id.selectRoomsButton);

        selectRoomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationActivity.this, SelectRoomsActivity.class);
                intent.putExtra("startDate",checkInDatePickerButton.getText());
                intent.putExtra("endDate",checkOutDatePickerButton.getText());
                intent.putExtra("username", username);
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }
        });
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month += 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePickers() {
        initStartDatePicker();
        initEndDatePicker();
    }

    private void initStartDatePicker() {
        DatePickerDialog.OnDateSetListener startDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String newDate = makeDateString(day, month, year);

                checkInDatePickerButton.setText(newDate);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialogStart = new DatePickerDialog(this, style, startDateSetListener, year, month, day);
    }

    private void initEndDatePicker() {
        DatePickerDialog.OnDateSetListener endDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String newDate = makeDateString(day, month, year);

                checkOutDatePickerButton.setText(newDate);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialogEnd = new DatePickerDialog(this, style, endDateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        String[] monthsNames = new String[]{"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        if (month >= 1 && month <= 12) {
            return monthsNames[month - 1];
        } else {
            return "JAN"; // default should never happen
        }
    }

    public void openStartDatePicker(View view) {
        datePickerDialogStart.show();
    }

    public void openEndDatePicker(View view) {
        datePickerDialogEnd.show();
    }

//    navbar methode
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
            intent = new Intent(ReservationActivity.this, HotelProfile.class);
            intent.putExtra("username", username);
            intent.putExtra("email", email);
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
            intent = new Intent(ReservationActivity.this, AdsActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("email", email);
            startActivity(intent);
            finish();
            break;

        case R.id.nav_account:
            Toast.makeText(this, "account!", Toast.LENGTH_SHORT).show();
            intent = new Intent(ReservationActivity.this, User_Profile.class);
            intent.putExtra("username", username);
            intent.putExtra("email", email);
            startActivity(intent);
            finish();
            break;

        case R.id.nav_logout:
            Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
            intent = new Intent(ReservationActivity.this, LoginActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("email", email);
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