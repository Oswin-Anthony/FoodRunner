package com.example.foodrunner;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class favourite_fragment extends Fragment implements recycler_view_interface{
    RecyclerView rc_view;
    String[] res_name={"All Vegan",
            "Ichiraku Ramen",
            "Burrito Express",
            "Khaldi Cafe"};
    String[] rate_per_person={"4,000 ₩", "5,000 ₩", "5,000 ₩", "9,000 ₩"};
    String[] res_rating={"3.5", "4.6", "3.9", "3.8"};
    int[] images = {R.mipmap.img_salad,
            R.mipmap.img_ramen,
            R.mipmap.img_burrito,
            R.mipmap.img_waffle};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.favourite_fragment, container, false);
        rc_view = view.findViewById(R.id.favourites_recycler_view);
        rc_view.setLayoutManager(new LinearLayoutManager(this.getContext()));
        //rc_view.setAdapter(new recycler_helper(res_name, rate_per_person, res_rating, images, this));

        return view;
    }

    @Override
    public void on_item_clicked(int position) {
        Intent intent = new Intent(getActivity(), restaurant_details.class);
        intent.putExtra("res_name", res_name[position]);
        startActivity(intent);
    }
}