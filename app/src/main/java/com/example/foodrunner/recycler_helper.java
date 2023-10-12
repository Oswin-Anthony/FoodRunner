package com.example.foodrunner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycler_helper extends RecyclerView.Adapter<recycler_helper.MyViewHolder> {
    int[] img;
    ArrayList<fs_res_helper> res_list;
    private final recycler_view_interface rc_interface;

    public recycler_helper(ArrayList<fs_res_helper> list, int[] images, recycler_view_interface rc_interface) {
        img = images;
        res_list = list;
        this.rc_interface = rc_interface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_helper, parent, false);
        return new MyViewHolder(v, rc_interface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        fs_res_helper data = res_list.get(position);
        holder.name.setText(data.get_name());
        holder.cost.setText((int) data.get_cost());
        holder.rating.setText((int) data.get_rating());
        holder.img.setImageResource(img[position]);
    }

    @Override
    public int getItemCount() {
        return res_list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, cost, rating;
        ImageView img;

        public MyViewHolder(@NonNull View itemView, recycler_view_interface rc_interface) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_res_name);
            cost = itemView.findViewById(R.id.txt_res_rate);
            rating = itemView.findViewById(R.id.txt_res_rating);
            img = itemView.findViewById(R.id.img_res);

            itemView.setOnClickListener(v -> {
                if(rc_interface != null){
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION)
                        rc_interface.on_item_clicked(pos);
                }
            });
        }
    }
}
