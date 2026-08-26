package com.example.framxpert;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpertAdapter extends RecyclerView.Adapter<ExpertAdapter.ViewHolder> {

    Context context;
    ArrayList<ExpertModel> expertList;

    public ExpertAdapter(Context context, ArrayList<ExpertModel> expertList) {
        this.context = context;
        this.expertList = expertList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.expert_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ExpertModel model = expertList.get(position);

        holder.txtExpertName.setText(model.getName());
        holder.txtSpecialization.setText(model.getSpecialization());

        // Call
        holder.btnCall.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + model.getPhone()));
            context.startActivity(intent);

        });

        // WhatsApp
        holder.btnWhatsapp.setOnClickListener(v -> {

            try {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://wa.me/91" + model.getPhone()));
                context.startActivity(intent);

            } catch (Exception e) {

                Toast.makeText(context,
                        "WhatsApp not installed",
                        Toast.LENGTH_SHORT).show();

            }

        });

        // Email
        holder.btnEmail.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + model.getEmail()));
            intent.putExtra(Intent.EXTRA_SUBJECT,
                    "Need Agriculture Advice");

            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return expertList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtExpertName, txtSpecialization;
        Button btnCall, btnWhatsapp, btnEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtExpertName = itemView.findViewById(R.id.txtExpertName);
            txtSpecialization = itemView.findViewById(R.id.txtSpecialization);

            btnCall = itemView.findViewById(R.id.btnCall);
            btnWhatsapp = itemView.findViewById(R.id.btnWhatsapp);
            btnEmail = itemView.findViewById(R.id.btnEmail);
        }
    }
}