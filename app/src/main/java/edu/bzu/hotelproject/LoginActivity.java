package edu.bzu.hotelproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    public static final String EMAIL = "EMAIL";
    public static final String PASS = "PASS";
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
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = EmailAddress.getText().toString();
                String password = edtPassword.getText().toString();

                if(chk.isChecked()){
                    if(!flag) {
                        editor.putString(EMAIL, e);
                        editor.putString(PASS, password);
                        editor.putBoolean(FLAG, true);
                        editor.commit();
                    }
                }
                Intent intent = new Intent(LoginActivity.this, HotelProfile.class);
                startActivity(intent);
                finish();
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
            String email = prefs.getString(EMAIL, "");
            String password = prefs.getString(PASS, "");
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