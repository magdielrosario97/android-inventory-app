package com.snhu.inventoryapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context;
    private List<Item> itemList;

    // Create adapter with app context and list of items
    public ItemAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    // Create new card layout for item
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate inventory card layout
        View view = LayoutInflater.from(context).inflate(R.layout.inventory_card, parent, false);
        // Return new card layout
        return new ItemViewHolder(view);
    }

    // Binds data from list to UI
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Get item at current position
        Item item = itemList.get(position);
        // Set image, name, stock, and sku in card layout
        holder.image.setImageResource(item.getImageId());
        holder.name.setText(item.getName());
        holder.stock.setText("Stock: " + item.getStock());
        holder.sku.setText("SKU: " + item.getSku());

        // Handles click to open EditItemActivity
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, EditItemActivity.class);
            intent.putExtra("id", item.getId());
            intent.putExtra("imageId", item.getImageId());
            intent.putExtra("name", item.getName());
            intent.putExtra("stock", item.getStock());
            intent.putExtra("sku", item.getSku());
            context.startActivity(intent);
        });
    }

    // Returns total num of items
    @Override
    public int getItemCount() {
        return itemList.size();
    }


    // Holds each item's inventory card views
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView name, sku, stock;
        ImageView image;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ItemName);
            sku = itemView.findViewById(R.id.ItemSKU);
            stock = itemView.findViewById(R.id.ItemStock);
            image = itemView.findViewById(R.id.ItemImage);
        }
    }
}
