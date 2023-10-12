package com.example.foodrunner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_pswd extends AppCompatActivity {
    Button btn_next;
    ImageView img_back_arrow;
    EditText txt_enter_email;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_pswd);

        mAuth = FirebaseAuth.getInstance();

        img_back_arrow = findViewById(R.id.img_back_arrow);
        img_back_arrow.setOnClickListener(view -> {
            startActivity(new Intent(forgot_pswd.this, sign_in_screen.class));
            finish();
        });

        btn_next = findViewById(R.id.btn_forgot_pswd_next);
        btn_next.setOnClickListener(view -> on_click_next());
    }

    public void on_click_next() {
        txt_enter_email = findViewById(R.id.enter_email);
        String email = txt_enter_email.getText().toString();

        if(email.isEmpty()) {
            txt_enter_email.setError("Email cannot be empty");
            txt_enter_email.requestFocus();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txt_enter_email.setError("Email is not valid");
            txt_enter_email.requestFocus();
        }
        else {
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(forgot_pswd.this, "Password change email sent", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(forgot_pswd.this, sign_in_screen.class));
                    finish();
                }
                else
                    Toast.makeText(forgot_pswd.this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(forgot_pswd.this, sign_in_screen.class));
        finish();
    }
}