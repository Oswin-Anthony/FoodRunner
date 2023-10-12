package com.example.foodrunner;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class restaurant_details extends AppCompatActivity {
    RecyclerView rc_view;
    TextView txt_title;
    String[] food_name = {"Margherita Pizza","Pepperoni Pizza","BBQ Pizza","Veggie Pizza","Chicken Pizza","Bacon Pizza","Mexican Pizza","Gorgonzola Pizza"};
    String[] cost = {"₩4,000", "₩11,000", "₩5,000", "₩5,000 ", "₩8,000 ", "₩7,000", "₩13,000","₩9,000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);

        txt_title = findViewById(R.id.res_details_title);
        txt_title.setText(getIntent().getStringExtra("position"));

        rc_view = findViewById(R.id.details_recycler_view);
        rc_view.setLayoutManager(new LinearLayoutManager(this));
        rc_view.setAdapter(new recycler_helper_det(food_name, cost));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}