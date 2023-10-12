package com.example.foodrunner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class home_fragment extends Fragment implements recycler_view_interface {
    RecyclerView rc_view;
    int[] images = {R.mipmap.img_salad,
            R.mipmap.img_lasagna,
            R.mipmap.img_ramen,
            R.mipmap.img_burrito,
            R.mipmap.img_pizza,
            R.mipmap.img_indian,
            R.mipmap.img_french,
            R.mipmap.img_waffle};
    recycler_helper rc_helper;
    DatabaseReference db;
    ArrayList<fs_res_helper> res_list;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = LayoutInflater.from(getContext()).inflate(R.layout.home_fragment, container, false);
        /*
        rc_view = view.findViewById(R.id.home_recycler_view);
        db = FirebaseDatabase.getInstance().getReference("restaurants");
        rc_view.setHasFixedSize(true);
        rc_view.setLayoutManager(new LinearLayoutManager(this.getContext()));
        res_list = new ArrayList<>();
        rc_helper = new recycler_helper(res_list, images, this);
        rc_view.setAdapter(rc_helper);

        db.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        fs_res_helper res = data.getValue(fs_res_helper.class);
                        res_list.add(res);
                    }
                    rc_helper.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
           */
        return view;
    }

    @Override
    public void on_item_clicked(int position) {
        Intent intent = new Intent(getActivity(), restaurant_details.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}