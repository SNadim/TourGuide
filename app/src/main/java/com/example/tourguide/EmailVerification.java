package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class EmailVerification extends AppCompatActivity {
    Button verifyEmail;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        verifyEmail = findViewById(R.id.btnEmailVerification);
        auth = FirebaseAuth.getInstance();

        verifyEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // send verification email

                auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(EmailVerification.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),UserProfile.class));
                        finish();
                    }
                });
            }
        });
    }
}