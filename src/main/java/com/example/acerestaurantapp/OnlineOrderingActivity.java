package com.example.acerestaurantapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class OnlineOrderingActivity extends AppCompatActivity {

    // Declare UI elements and variables
    private RecyclerView recyclerViewOrderingItems;
    private ItemsAdapter itemsAdapter;
    private TextView cartSummaryDetails;
    private int totalItems = 0;
    private double totalPrice = 0.0;

    // Example method to fetch data
    private List<Item> fetchData() {
        // Dummy data for now, replace with actual data fetching logic from fetchItemsFromDataSource
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item 1", "$10.00", 4.5, "Delicious item 1", R.drawable.item_placeholder));
        items.add(new Item("Item 2", "$15.00", 4.7, "Delicious item 2", R.drawable.item_placeholder));
        // ... add more items
        return items;
    }

    // Method to fetch items from a data source
    private List<Item> fetchItemsFromDataSource() {
        // Replace with actual data fetching logic from a database or API
        List<Item> items = new ArrayList<>();

        // Assume fetchData() is a method that fetches the data from your data source
        items = fetchData();
        return items;
    }

    // Method to display items in the RecyclerView
    @SuppressLint("NotifyDataSetChanged")
    private void displayItems() {
        // Fetch items from the database or an API
        List<Item> items = fetchItemsFromDataSource();
        // Set items to the adapter and notify data change
        itemsAdapter.setItems(items);
        itemsAdapter.notifyDataSetChanged();
    }


    // Method to update cart summary details
    private void updateCartSummary() {
        // Set the text for cart summary details
        cartSummaryDetails.setText("Items: " + totalItems + "\nTotal: $" + String.format("%.2f", totalPrice));
    }

    // Method to handle adding item to cart
    private void addToCart(Item item) {
        totalItems++;
        totalPrice += Double.parseDouble(item.getPrice().substring(1));
        updateCartSummary();
    }

    // Method to simulate checkout process
    private void simulateCheckout() {
        // Example: Display a message or perform further actions
        Toast.makeText(OnlineOrderingActivity.this, "Checkout completed!", Toast.LENGTH_SHORT).show();

        // Example: Clear cart after successful checkout
        clearCart();
    }

    // Method to clear cart after checkout
    private void clearCart() {
        totalItems = 0;
        totalPrice = 0.0;
        updateCartSummary();
        itemsAdapter.setItems(new ArrayList<>());
        itemsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_ordering);

        // Initialize RecyclerView
        recyclerViewOrderingItems = findViewById(R.id.recycler_view_ordering_items);
        recyclerViewOrderingItems.setLayoutManager(new LinearLayoutManager(this));
        itemsAdapter = new ItemsAdapter(new ArrayList<>(), new ItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                addToCart(item);
            }
        });
        recyclerViewOrderingItems.setAdapter(itemsAdapter);

        // Initialize Cart Summary TextView
        cartSummaryDetails = findViewById(R.id.cart_summary_details);

        // Initialize Proceed to Checkout button
        Button buttonProceedToCheckout = findViewById(R.id.button_proceed_to_checkout);
        buttonProceedToCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Proceed to checkout logic here
                // This is a placeholder for actual implementation

                // Example: Validate checkout (dummy validation)
                if (totalItems > 0) {
                    // Calculate total price
                    // Example: double totalPrice = calculateTotalPrice();

                    // Simulate checkout process
                    simulateCheckout();
                } else {
                    // Show error message or handle no items in cart scenario
                    Toast.makeText(OnlineOrderingActivity.this, "Add items to cart first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Load and display items from the data source
        displayItems();
    }
}
