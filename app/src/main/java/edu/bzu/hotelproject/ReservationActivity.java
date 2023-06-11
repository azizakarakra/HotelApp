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
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Calendar;

public class ReservationActivity extends AppCompatActivity {

    ImageView menu, appLogo;

    private DatePickerDialog datePickerDialogStart, datePickerDialogEnd;
    private Button checkInDatePickerButton, checkOutDatePickerButton, selectRoomsButton;

    private Button showPopup;

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

        initDatePickers();
        checkInDatePickerButton = findViewById(R.id.checkInDatePickerButton);
        checkInDatePickerButton.setText(getTodaysDate());
        checkOutDatePickerButton = findViewById(R.id.checkOutDatePickerButton);
        checkOutDatePickerButton.setText(getTodaysDate());

        checkInDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStartDatePicker(view);
            }
        });
        checkOutDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEndDatePicker(view);
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
}