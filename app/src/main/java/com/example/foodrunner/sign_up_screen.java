package com.example.foodrunner;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Objects;

public class sign_up_screen extends AppCompatActivity {
    ImageView img_back_arrow;
    Button btn_signup_next;
    EditText txt_name, txt_email,txt_phone, txt_password, txt_conf_password;
    FirebaseAuth mAuth;
    FirebaseFirestore fbfs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_screen);

        img_back_arrow = findViewById(R.id.img_back_arrow);
        img_back_arrow.setOnClickListener(view -> {
            startActivity(new Intent(sign_up_screen.this, sign_in_screen.class));
            finish();
        });

        mAuth = FirebaseAuth.getInstance();
        fbfs = FirebaseFirestore.getInstance();

        btn_signup_next = findViewById(R.id.btn_signup);
        btn_signup_next.setOnClickListener(view -> create_user());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(sign_up_screen.this, sign_in_screen.class));
        finish();
    }

    private void create_user() {
        String name, email, phone, password, conf_password;
        txt_name = findViewById(R.id.enter_name);
        txt_email = findViewById(R.id.enter_email);
        txt_phone = findViewById(R.id.enter_phone_num);
        txt_password = findViewById(R.id.enter_pswd);
        txt_conf_password = findViewById(R.id.enter_confirm_pswd);
        name = txt_name.getText().toString();
        email = txt_email.getText().toString().trim();
        phone = txt_phone.getText().toString();
        password = txt_password.getText().toString().trim();
        conf_password = txt_conf_password.getText().toString().trim();

        if(TextUtils.isEmpty(name)) {
            txt_name.setError("Name cannot be empty!");
            txt_name.requestFocus();
        }
        else if(TextUtils.isEmpty(email)) {
            txt_email.setError("Email cannot be empty!");
            txt_email.requestFocus();
        }
        else if(TextUtils.isEmpty(password)) {
            txt_password.setError("Password cannot be empty!");
            txt_password.requestFocus();
        }
        else if(TextUtils.isEmpty(phone)) {
            txt_phone.setError("Mobile cannot be empty!");
            txt_phone.requestFocus();
        }
        else if(TextUtils.isEmpty(conf_password)) {
            txt_conf_password.setError("Passwords don't match");
            txt_conf_password.requestFocus();
        }
        else if(!TextUtils.equals(password, conf_password)) {
            txt_conf_password.setError("Passwords don't match");
            txt_conf_password.requestFocus();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Toast.makeText(sign_up_screen.this, "New User Registered", Toast.LENGTH_SHORT).show();
                    fbfs.collection("User")
                            .document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                            .set(new fs_user_helper(email ,name, phone));
                    startActivity(new Intent(sign_up_screen.this, sign_in_screen.class));
                    finish();
                }
                else
                    Toast.makeText(sign_up_screen.this, "Error While Registering\n" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
            });
        }
    }
}