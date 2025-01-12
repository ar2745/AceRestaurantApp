package com.example.acerestaurantapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    // Declare an instance of the ItemsAdapter
    private ItemsAdapter itemsAdapter;
    private List<Item> allItems;
    private ImageView shoppingCartIcon;
    private TextView cartBadge;
    private List<Item> cartItems = new ArrayList<>();

    // Example method to fetch data by category
    private List<Item> fetchDataByCategory(String category) {
        List<Item> items = new ArrayList<>();

        switch (category) {
            case "Appetizers":
                items.add(new Item("Spring Rolls", "$5.00", 4.2, "Crispy and delicious", R.drawable.spring_rolls));
                items.add(new Item("Garlic Bread", "$4.00", 4.2, "Toasted with garlic and butter", R.drawable.garlic_bread));
                break;
            case "Entrees":
                items.add(new Item("Grilled Chicken", "$15.00", 4.2, "Served with vegetables", R.drawable.grilled_chicken));
                items.add(new Item("Pasta", "$12.00", 4.2, "Served with marinara sauce", R.drawable.pasta));
                break;
            case "Desserts":
                items.add(new Item("Cheesecake", "$6.00", 4.2, "Rich and creamy", R.drawable.cheesecake));
                items.add(new Item("Ice Cream", "$3.00", 4.2, "Vanilla, chocolate, or strawberry", R.drawable.ice_cream));
                break;
            case "Drinks":
                items.add(new Item("Coca Cola", "$2.00", 4.2, "Refreshing soda", R.drawable.coca_cola));
                items.add(new Item("Lemonade", "$3.00", 4.2, "Fresh and tangy", R.drawable.lemonade));
                break;
        }
        return items;
    }

    // Method to fetch items from a data source based on the category
    private List<Item> fetchItemsFromDataSource(String category) {
        // fetchDataByCategory() is a method that fetches the data from the data source
        return fetchDataByCategory(category);
    }

    // Method to display items of a specific category
    @SuppressLint("NotifyDataSetChanged")
    public void displayItems(String category) {
        // Get items from the specified category
        List<Item> items = fetchItemsFromDataSource(category);

        // Set items to the adapter and notify data change
        itemsAdapter.setItems(items);
        itemsAdapter.notifyDataSetChanged();

        // Show a toast message indicating the category being displayed
        Toast.makeText(this, "Displaying " + category, Toast.LENGTH_SHORT).show();
    }

    // Fetch all items
    private List<Item> fetchAllItems() {
        List<Item> items = new ArrayList<>();

        // Add all items here
        items.add(new Item("Spring Rolls", "$5.00", 4.2, "Crispy and delicious", R.drawable.spring_rolls));
        items.add(new Item("Garlic Bread", "$4.00", 4.2, "Toasted with garlic and butter", R.drawable.garlic_bread));
        items.add(new Item("Grilled Chicken", "$15.00", 4.2, "Served with vegetables", R.drawable.grilled_chicken));
        items.add(new Item("Pasta", "$12.00", 4.2, "Served with marinara sauce", R.drawable.pasta));
        items.add(new Item("Cheesecake", "$6.00", 4.2, "Rich and creamy", R.drawable.cheesecake));
        items.add(new Item("Ice Cream", "$3.00", 4.2, "Vanilla, chocolate, or strawberry", R.drawable.ice_cream));
        items.add(new Item("Coca Cola", "$2.00", 4.2, "Refreshing soda", R.drawable.coca_cola));
        items.add(new Item("Lemonade", "$3.00", 4.2, "Fresh and tangy", R.drawable.lemonade));

        return items;
    }

    // Filter items based on the search query
    @SuppressLint("NotifyDataSetChanged")
    private void filterItems(String query) {
        List<Item> filteredItems = new ArrayList<>();
        for (Item item : allItems) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredItems.add(item);
            }
        }
        itemsAdapter.setItems(filteredItems);
        itemsAdapter.notifyDataSetChanged();
    }

    // Add item to cart
    private void addToCart(Item item) {
        cartItems.add(item);
        updateCartBadge(cartItems.size());
        Toast.makeText(this, item.getName() + " added to cart", Toast.LENGTH_SHORT).show();
    }

    // Get cart item count
    private int getCartItemCount() {
        return cartItems.size();
    }

    // Update cart badge
    private void updateCartBadge(int itemCount) {
        if (itemCount > 0) {
            cartBadge.setText(String.valueOf(itemCount));
            cartBadge.setVisibility(View.VISIBLE);
        } else {
            cartBadge.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Initialize buttons for different categories
        Button buttonAppetizers = findViewById(R.id.button_appetizers);
        Button buttonEntrees = findViewById(R.id.button_entrees);
        Button buttonDesserts = findViewById(R.id.button_desserts);
        Button buttonDrinks = findViewById(R.id.button_drinks);

        // Initialize RecyclerView for displaying items
        RecyclerView recyclerViewItems = findViewById(R.id.recycler_view_items);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));

        // Fetch all items
        allItems = fetchAllItems();

        // Initialize ItemsAdapter
        itemsAdapter = new ItemsAdapter(new ArrayList<>(), new ItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                // Handle item click, if needed
            }
        });
        recyclerViewItems.setAdapter(itemsAdapter);

        // Initialize SearchView
        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterItems(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterItems(newText);
                return true;
            }
        });

        // Initialize the shopping cart icon
        shoppingCartIcon = findViewById(R.id.shopping_cart_icon);
        cartBadge = findViewById(R.id.cart_badge);

        shoppingCartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the shopping cart activity
                Intent intent = new Intent(MenuActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });

        // Set click listeners for category buttons to display corresponding items
        buttonAppetizers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayItems("Appetizers");
            }
        });

        buttonEntrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayItems("Entrees");
            }
        });

        buttonDesserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayItems("Desserts");
            }
        });

        buttonDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayItems("Drinks");
            }
        });

        // Display the default category when the activity is created
        displayItems("Appetizers");

        // Initialize cart badge with current cart item count
        int cartItemCount = getCartItemCount();
        updateCartBadge(cartItemCount);
    }
}

