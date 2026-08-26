package com.example.framxpert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.ViewHolder> {

    Context context;
    ArrayList<marketModel> marketList;

    public MarketAdapter(Context context, ArrayList<marketModel> marketList) {
        this.context = context;
        this.marketList = marketList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.market_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        marketModel model = marketList.get(position);

        holder.txtCrop.setText(model.getCrop());
        holder.txtMarket.setText("Market : " + model.getMarket());
        holder.txtPrice.setText("Price : " + model.getPrice());

    }

    @Override
    public int getItemCount() {
        return marketList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtCrop, txtMarket, txtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCrop = itemView.findViewById(R.id.txtCrop);
            txtMarket = itemView.findViewById(R.id.txtMarket);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}
