package com.example.framxpert;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CropAdapter extends RecyclerView.Adapter<CropAdapter.ViewHolder> {

    Context context;
    ArrayList<CropModel> cropList;


    public CropAdapter(Context context, ArrayList<CropModel> cropList) {
        this.context = context;
        this.cropList = cropList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.cropitem, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CropModel crop = cropList.get(position);


        holder.imgCrop.setImageResource(crop.getImage());
        holder.txtCropName.setText(crop.getCropName());


        holder.txtSeason.setText(
                "Best season : " + crop.getSeason()
        );


        // Arrow click next page open

        holder.imgNext.setOnClickListener(v -> {

            Intent intent = new Intent(context, CropDetailsActivity.class);


            intent.putExtra("image", crop.getImage());
            intent.putExtra("cropName", crop.getCropName());
            intent.putExtra("temperature", crop.getTemperature());
            intent.putExtra("soil", crop.getSoil());
            intent.putExtra("water", crop.getWater());
            intent.putExtra("duration", crop.getDuration());
            intent.putExtra("fertilizer", crop.getFertilizer());
            intent.putExtra("disease", crop.getDisease());
            intent.putExtra("description", crop.getDescription());


            context.startActivity(intent);

        });


        // Full card click pan open hoil

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, CropDetailsActivity.class);


            intent.putExtra("image", crop.getImage());
            intent.putExtra("cropName", crop.getCropName());
            intent.putExtra("temperature", crop.getTemperature());
            intent.putExtra("soil", crop.getSoil());
            intent.putExtra("water", crop.getWater());
            intent.putExtra("duration", crop.getDuration());
            intent.putExtra("fertilizer", crop.getFertilizer());
            intent.putExtra("disease", crop.getDisease());
            intent.putExtra("description", crop.getDescription());


            context.startActivity(intent);

        });

    }


    @Override
    public int getItemCount() {
        return cropList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {


        ImageView imgCrop, imgNext;
        TextView txtCropName, txtSeason;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            imgCrop = itemView.findViewById(R.id.imgCrop);
            imgNext = itemView.findViewById(R.id.imgNext);


            txtCropName = itemView.findViewById(R.id.txtCropName);
            txtSeason = itemView.findViewById(R.id.txtSeason);

        }
    }
}