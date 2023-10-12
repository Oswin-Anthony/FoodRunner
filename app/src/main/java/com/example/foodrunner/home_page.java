package com.example.foodrunner;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Objects;

public class home_page extends AppCompatActivity {
    TextView txt_title, txt_user_email, txt_user_name;
    ImageView img_profile;
    View hview;
    FirebaseFirestore fbfs;
    String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        NavigationView navigation_view = findViewById(R.id.navigation_view);
        hview = navigation_view.getHeaderView(0);

        txt_user_email = hview.findViewById(R.id.txt_user_email);
        txt_user_name = hview.findViewById(R.id.txt_user_name);
        img_profile = hview.findViewById(R.id.img_menu);

        fbfs = FirebaseFirestore.getInstance();
        fbfs.collection("User")
                .document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if(queryDocumentSnapshots.exists()) {
                        name = queryDocumentSnapshots.getString("name");
                        txt_user_name.setText(name);
                        email = queryDocumentSnapshots.getString("email");
                        txt_user_email.setText(email);
                    }
                });

        final DrawerLayout drawerlayout = findViewById(R.id.drawer_layout);
        findViewById(R.id.img_menu).setOnClickListener(view -> drawerlayout.openDrawer(GravityCompat.START) );

        NavController nav_controller = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigation_view, nav_controller);

        txt_title = findViewById(R.id.txt_title);
        nav_controller.addOnDestinationChangedListener((navController, navDestination, bundle) -> txt_title.setText(navDestination.getLabel()));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Do you want to exit?")
                .setPositiveButton("Yes, Exit", (dialog, which) -> System.exit(0))
                .setNegativeButton("Cancel", (dialogInterface, i) -> {})
                .show();
    }
}