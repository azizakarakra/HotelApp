package edu.bzu.hotelproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class User_Profile extends AppCompatActivity {

    ImageView menu, appLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        menu = (ImageView) findViewById(R.id.menu);
        appLogo = (ImageView) findViewById(R.id.appLogo);

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

        appLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_Profile.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

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
}