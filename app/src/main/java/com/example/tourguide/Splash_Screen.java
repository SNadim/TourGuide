package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Splash_Screen extends AppCompatActivity {

    TextView logoname;
    ImageView logo;
    LottieAnimationView lottie;
    EditText name;

    Animation logonameanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        logo  = findViewById(R.id.logo);
        logoname  = findViewById(R.id.logo_name);
        lottie   = findViewById(R.id.animationView);


        logonameanim = AnimationUtils.loadAnimation(this,R.anim.logo_name_anim);

        logoname.setAnimation(logonameanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =  new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
}