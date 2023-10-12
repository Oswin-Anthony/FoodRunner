package com.example.foodrunner;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class sign_in_screen extends AppCompatActivity {
    TextView txt_forgot_pswd, txt_sign_up;
    Button btn_sign_in;
    EditText txt_email, txt_password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_screen);

        mAuth = FirebaseAuth.getInstance();

        txt_forgot_pswd = findViewById(R.id.txt_forgotpswd);
        txt_forgot_pswd.setOnClickListener(view -> {
            startActivity(new Intent(sign_in_screen.this, forgot_pswd.class));
            finish();
        });

        txt_sign_up = findViewById(R.id.txt_signup);
        txt_sign_up.setOnClickListener(view -> {
            startActivity(new Intent(sign_in_screen.this, sign_up_screen.class));
            finish();
        });

        btn_sign_in = findViewById(R.id.btn_signin);
        btn_sign_in.setOnClickListener(view -> login_user());
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Do you want to exit?")
                .setPositiveButton("Yes, Exit", (dialog, which) -> System.exit(0))
                .setNegativeButton("Cancel", (dialogInterface, i) -> {})
                .show();
    }

    private void login_user() {
        String email, password;
        txt_email = findViewById(R.id.enter_email);
        txt_password = findViewById(R.id.enter_pswd);
        email = txt_email.getText().toString().trim();
        password = txt_password.getText().toString();

        if(TextUtils.isEmpty(email)) {
            txt_email.setError("Email cannot be empty!");
            txt_email.requestFocus();
        }
        else if(TextUtils.isEmpty(password)) {
            txt_password.setError("Password cannot be empty!");
            txt_password.requestFocus();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Toast.makeText(sign_in_screen.this, "Signin Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(sign_in_screen.this, home_page.class));
                    finish();
                }
                else Toast.makeText(sign_in_screen.this, "Error While Signing In\n"
                        + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
            });
        }
    }
}