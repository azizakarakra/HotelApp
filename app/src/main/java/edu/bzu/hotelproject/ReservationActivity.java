package edu.bzu.hotelproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Calendar;

public class ReservationActivity extends AppCompatActivity {

    ImageView menu, appLogo;

    private DatePickerDialog datePickerDialog;
    private Button checkInDatePickerButton;

    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        appLogo = (ImageView) findViewById(R.id.appLogo);

        appLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationActivity.this, HotelProfile.class);
                startActivity(intent);
                finish();
            }
        });

        initDatePicker();
        checkInDatePickerButton = findViewById(R.id.checkInDatePickerButton);
        checkInDatePickerButton.setText(getTodaysDate());

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View viewPopupwindow = layoutInflater.inflate(R.layout.popup_window, null);

                // Find views within the popup layout
                Button addFloorNumber = viewPopupwindow.findViewById(R.id.addFloorNumber);
                Button subFloorNumber = viewPopupwindow.findViewById(R.id.subFloorNumber);
                TextView floorNumber = viewPopupwindow.findViewById(R.id.floorNumber);

                // Set click listeners for the buttons
                addFloorNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int number = Integer.parseInt(floorNumber.getText().toString());
                        if (number < 30) {
                            number++;
                            floorNumber.setText(String.valueOf(number));
                        }
                    }
                });

                subFloorNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int number = Integer.parseInt(floorNumber.getText().toString());
                        if (number > 0) {
                            number--;
                            floorNumber.setText(String.valueOf(number));
                        }
                    }
                });

                // Find views within the popup layout
                Button addRoomsNumber = viewPopupwindow.findViewById(R.id.addRoomsNumber);
                Button subRoomsNumber = viewPopupwindow.findViewById(R.id.subRoomsNumber);
                TextView roomsNumber = viewPopupwindow.findViewById(R.id.roomsNumber);

                // Set click listeners for the buttons
                addRoomsNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int number = Integer.parseInt(roomsNumber.getText().toString());
                        if (number < 30) {
                            number++;
                            roomsNumber.setText(String.valueOf(number));
                        }
                    }
                });

                subRoomsNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int number = Integer.parseInt(roomsNumber.getText().toString());
                        if (number > 0) {
                            number--;
                            roomsNumber.setText(String.valueOf(number));
                        }
                    }
                });

                // Find views within the popup layout
                Button addAdultsNumber = viewPopupwindow.findViewById(R.id.addAdultsNumber);
                Button subAdultsNumber = viewPopupwindow.findViewById(R.id.subAdultsNumber);
                TextView adultsNumber = viewPopupwindow.findViewById(R.id.adultsNumber);

                // Set click listeners for the buttons
                addAdultsNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int number = Integer.parseInt(adultsNumber.getText().toString());
                        if (number < 30) {
                            number++;
                            adultsNumber.setText(String.valueOf(number));
                        }
                    }
                });

                subAdultsNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int number = Integer.parseInt(adultsNumber.getText().toString());
                        if (number > 0) {
                            number--;
                            adultsNumber.setText(String.valueOf(number));
                        }
                    }
                });

                // Find views within the popup layout
                Button addChildrenNumber = viewPopupwindow.findViewById(R.id.addChildrenNumber);
                Button subChildrenNumber = viewPopupwindow.findViewById(R.id.subChildrenNumber);
                TextView childrenNumber = viewPopupwindow.findViewById(R.id.childrenNumber);

                // Set click listeners for the buttons
                addChildrenNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int number = Integer.parseInt(childrenNumber.getText().toString());
                        if (number < 30) {
                            number++;
                            childrenNumber.setText(String.valueOf(number));
                        }
                    }
                });

                subChildrenNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int number = Integer.parseInt(childrenNumber.getText().toString());
                        if (number > 0) {
                            number--;
                            childrenNumber.setText(String.valueOf(number));
                        }
                    }
                });

                // Find views within the popup layout
                Button apply = viewPopupwindow.findViewById(R.id.apply);
                Button cancel = viewPopupwindow.findViewById(R.id.cancel);
                ConstraintLayout constraintLayout = viewPopupwindow.findViewById(R.id.root);

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


    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                checkInDatePickerButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
}