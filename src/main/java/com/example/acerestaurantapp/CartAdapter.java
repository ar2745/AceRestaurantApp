package com.example.acerestaurantapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    // List to hold the items in the cart
    private List<Item> cartItems;

    // Constructor to initialize the adapter with cart items
    public CartAdapter(List<Item> cartItems) {
        this.cartItems = cartItems;
    }

    // Method to set new items to the adapter
    public void setItems(List<Item> cartItems) {
        this.cartItems = cartItems;
    }

    // Called when RecyclerView needs a new ViewHolder of the given type
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item_cart layout to create a new View for the ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    // Called by RecyclerView to display the data at the specified position
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        // Get the item at the current position
        Item item = cartItems.get(position);

        // Bind the item data to the ViewHolder views
        holder.itemNameTextView.setText(item.getName());
        holder.itemPriceTextView.setText(item.getPrice());
        holder.itemImageView.setImageResource(item.getImageResource());
    }

    // Returns the total number of items in the cart
    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    // ViewHolder class to hold the views for each item in the RecyclerView
    static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView itemNameTextView;
        TextView itemPriceTextView;
        ImageView itemImageView;

        // Constructor to initialize the views
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.item_name);
            itemPriceTextView = itemView.findViewById(R.id.item_price);
            itemImageView = itemView.findViewById(R.id.item_image);
        }
    }
}
