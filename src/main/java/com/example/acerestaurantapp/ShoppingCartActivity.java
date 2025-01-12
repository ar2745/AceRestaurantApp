package com.example.acerestaurantapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    private CartAdapter cartAdapter;
    private TextView totalAmountTextView;

    private List<Item> getCartItems() {
        // Fetch items from the cart
        // For demonstration, returning some dummy items but this should mainly just return items
        List<Item> items = new ArrayList<>();
        items.add(new Item("Spring Rolls", "$5.00", 4.2, "Crispy and delicious", R.drawable.spring_rolls));
        items.add(new Item("Grilled Chicken", "$15.00", 4.2, "Served with vegetables", R.drawable.grilled_chicken));
        return items;
    }

    // Calculate cart total amount
    private double calculateTotalAmount(List<Item> items) {
        double total = 0.0;
        for (Item item : items) {
            String priceString = item.getPrice().replace("$", "");
            double price = Double.parseDouble(priceString);
            total += price;
        }
        return total;
    }

    // Method for cart items to be loaded
    private void loadCartItems() {
        // Fetch items in the cart
        List<Item> cartItems = getCartItems();

        // Set items to the adapter
        cartAdapter.setItems(cartItems);
        cartAdapter.notifyDataSetChanged();

        // Calculate and display the total amount
        double totalAmount = calculateTotalAmount(cartItems);
        totalAmountTextView.setText(String.format("$%.2f", totalAmount));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        // Initialize RecyclerView for displaying cart items
        RecyclerView recyclerViewCartItems = findViewById(R.id.recycler_view_cart);
        recyclerViewCartItems.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter
        cartAdapter = new CartAdapter(new ArrayList<>());
        recyclerViewCartItems.setAdapter(cartAdapter);

        // Initialize total amount TextView
        totalAmountTextView = findViewById(R.id.total_amount);

        // Load cart items
        loadCartItems();
    }
}
