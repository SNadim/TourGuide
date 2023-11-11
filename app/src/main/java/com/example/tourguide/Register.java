package com.example.tourguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText registerFullName,registerEmail,registerPassword,registerConfirmPassword;
    Button registerUserBtn;
    TextView redirectToLogin;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerFullName = findViewById(R.id.inputName);
        registerEmail = findViewById(R.id.inputEmail);
        registerPassword = findViewById(R.id.inputPassword);
        registerConfirmPassword = findViewById(R.id.inputConfirmPassword);
        registerUserBtn = findViewById(R.id.btnRegister);
        redirectToLogin = findViewById(R.id.goToLogin);
        fAuth = FirebaseAuth.getInstance();

        redirectToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

        registerUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // extract the data from the user

                String fullName = registerFullName.getText().toString();
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String confirmPassword = registerConfirmPassword.getText().toString();

                // data validation

                if(fullName.isEmpty()) {
                    registerFullName.setError("Full name is required");
                    return;
                }
                if(email.isEmpty()) {
                    registerEmail.setError("Email is required");
                    return;
                }
                if(password.isEmpty()) {
                    registerPassword.setError("Password is required");
                    return;
                }
                if(confirmPassword.isEmpty()) {
                    registerConfirmPassword.setError("Confirm Password is required");
                    return;
                }

                if(!password.equals(confirmPassword)) {
                    registerConfirmPassword.setError("Password do not match");
                    return;
                }
                Toast.makeText(Register.this, "Data Validated", Toast.LENGTH_SHORT).show();

                fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        //Get user email and uid from auth
                        String email = fAuth.getCurrentUser().getEmail();
                        String uid = fAuth.getCurrentUser().getUid();
                        System.out.println(email);


                        // send to Next page
                        startActivity((new Intent(getApplicationContext(),EmailVerification.class)));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });

            }
        });
    }
}