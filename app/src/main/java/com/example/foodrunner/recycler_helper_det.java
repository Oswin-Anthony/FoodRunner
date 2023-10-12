package com.example.foodrunner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recycler_helper_det extends RecyclerView.Adapter<recycler_helper_det.MyViewHolder> {
    String[] data1, data2;

    public recycler_helper_det(String[] food_name, String[] cost) {
        data1 = food_name;
        data2 = cost;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_helper_det, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull recycler_helper_det.MyViewHolder holder, int position) {
        holder.name.setText(data1[position]);
        holder.rate.setText(data2[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, rate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_food_name);
            rate = itemView.findViewById(R.id.txt_food_rate);
        }
    }
}
