package com.example.acerestaurantapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<Item> items;  // List to hold the items to be displayed
    private final OnItemClickListener listener;  // Listener interface for item click events

    // Interface for item click events
    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    // Constructor to initialize the adapter with items and click listener
    public ItemsAdapter(List<Item> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    // Method to set new items to the adapter
    public void setItems(List<Item> items) {
        this.items = items;
    }

    // Called when RecyclerView needs a new ViewHolder of the given type to represent an item
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item view layout and create a new ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ItemViewHolder(view);
    }

    // Called by RecyclerView to display the data at the specified position
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Bind data to the ViewHolder at the given position
        Item item = items.get(position);
        holder.bind(item, listener);  // Call bind method of ViewHolder to bind data and listener
    }

    // Returns the total number of items in the data set held by the adapter
    @Override
    public int getItemCount() {
        return items.size();
    }

    // ViewHolder class to hold references to the views for each item
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView priceTextView;
        TextView descriptionTextView;
        ImageView itemImageView;

        // Constructor to initialize views
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.item_name);
            priceTextView = itemView.findViewById(R.id.item_price);
            descriptionTextView = itemView.findViewById(R.id.item_description);
            itemImageView = itemView.findViewById(R.id.item_image);
        }

        // Method to bind data to views and set click listener
        public void bind(final Item item, final OnItemClickListener listener) {
            nameTextView.setText(item.getName());
            priceTextView.setText(item.getPrice());
            descriptionTextView.setText(item.getDescription());
            itemImageView.setImageResource(item.getImageResource());

            // Set click listener to handle item clicks
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);  // Invoke listener callback on item click
                }
            });
        }
    }
}
