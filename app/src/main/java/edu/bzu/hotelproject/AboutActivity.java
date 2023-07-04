package edu.bzu.hotelproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private ImageView i5;
    private Button b2;
    private TextView t5;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageView i1 = (ImageView) findViewById(R.id.hi);
        Animation a1 = AnimationUtils.loadAnimation(AboutActivity.this,R.anim.hi);
        i1.startAnimation(a1);

        ImageView i2 = (ImageView) findViewById(R.id.welcome);
        Animation a2 = AnimationUtils.loadAnimation(AboutActivity.this,R.anim.welcome);
        i2.startAnimation(a2);

        TextView t3 = (TextView) findViewById(R.id.textGuitar);
        Animation at3 = AnimationUtils.loadAnimation(AboutActivity.this,R.anim.guitar);
        t3.startAnimation(at3);
        ImageView i3 = (ImageView) findViewById(R.id.guitar);
        Animation a3 = AnimationUtils.loadAnimation(AboutActivity.this,R.anim.guitar);
        i3.startAnimation(a3);

        TextView t4 = (TextView) findViewById(R.id.textLove);
        Animation at4 = AnimationUtils.loadAnimation(AboutActivity.this,R.anim.love);
        t4.startAnimation(at4);
        ImageView i4 = (ImageView) findViewById(R.id.love);
        Animation a4 = AnimationUtils.loadAnimation(AboutActivity.this,R.anim.love);
        i4.startAnimation(a4);

        i5 = (ImageView) findViewById(R.id.party);
        b2 = (Button) findViewById(R.id.button3);
        t5 = (TextView) findViewById(R.id.textParty);
        b = (Button) findViewById(R.id.button2);

        Animation a5 = AnimationUtils.loadAnimation(AboutActivity.this,R.anim.party);
        i5.startAnimation(a5);

        Animation at5 = AnimationUtils.loadAnimation(AboutActivity.this,R.anim.party);
        t5.startAnimation(at5);

        Animation ab = AnimationUtils.loadAnimation(AboutActivity.this,R.anim.party);
        b.startAnimation(ab);

        Animation ab2 = AnimationUtils.loadAnimation(AboutActivity.this,R.anim.party);
        b2.startAnimation(ab2);


    }

    public void btnLogin(View view) {
        ImageView i6 = (ImageView) findViewById(R.id.letsgo);
        ConstraintLayout bac = findViewById(R.id.bac);
        i5.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        t5.setVisibility(View.INVISIBLE);
        b.setVisibility(View.INVISIBLE);

        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AboutActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
        bac.setVisibility(View.VISIBLE);
        i6.setVisibility(View.VISIBLE);
    }

    public void ToSignup(View view) {
        ImageView i6 = (ImageView) findViewById(R.id.angry);
        TextView t6 = (TextView) findViewById(R.id.textAngry);
        ConstraintLayout bac = findViewById(R.id.bac);

        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AboutActivity.this,SignupActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
        bac.setVisibility(View.VISIBLE);
        i6.setVisibility(View.VISIBLE);
        t6.setVisibility(View.VISIBLE);
    }

    public void toContact1(View view) {

    }

    public void About(View view) {
    }

    public void toHotel(View view) {
        Intent intent = new Intent(this,HotelProfile.class);
        startActivity(intent);
    }
}