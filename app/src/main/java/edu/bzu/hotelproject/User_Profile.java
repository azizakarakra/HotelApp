package edu.bzu.hotelproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class User_Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Spinner day_spinner = findViewById(R.id.day_spinner);
        Spinner month_spinner = findViewById(R.id.month_spinner);
        Spinner year_spinner = findViewById(R.id.year_spinner);

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

        months[0] = "January";
        months[1] = "February";
        months[2] = "March";
        months[3] = "April";
        months[4] = "May";
        months[5] = "June";
        months[6] = "July";
        months[7] = "August";
        months[8] = "September";
        months[9] = "October";
        months[10] = "November";
        months[11] = "December";

        return months;
    }
}