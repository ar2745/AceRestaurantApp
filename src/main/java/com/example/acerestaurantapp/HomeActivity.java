package com.example.acerestaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity {

    // Declare CardView variables for different sections
    private CardView sectionMenu, sectionOnlineOrdering, sectionReservation, sectionContact, sectionAbout;
    // Declare ImageView variables for bottom navigation icons
    private ImageView navMenu, navOnlineOrdering, navReservation, navContact, navAbout;

    // Method to navigate to the MenuActivity
    private void navigateToMenu() {
        Intent intent = new Intent(HomeActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    // Method to navigate to the OnlineOrderingActivity
    private void navigateToOnlineOrdering() {
        Intent intent = new Intent(HomeActivity.this, OnlineOrderingActivity.class);
        startActivity(intent);
    }

    // Method to navigate to the ReservationActivity
    private void navigateToReservation() {
        Intent intent = new Intent(HomeActivity.this, ReservationActivity.class);
        startActivity(intent);
    }

    // Method to navigate to the ContactActivity
    private void navigateToContact() {
        Intent intent = new Intent(HomeActivity.this, ContactActivity.class);
        startActivity(intent);
    }

    // Method to navigate to the AboutActivity
    private void navigateToAbout() {
        Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize clickable sections
        sectionMenu = findViewById(R.id.section_menu);
        sectionOnlineOrdering = findViewById(R.id.section_online_ordering);
        sectionReservation = findViewById(R.id.section_reservation);
        sectionContact = findViewById(R.id.section_contact);
        sectionAbout = findViewById(R.id.section_about);

        // Initialize bottom navigation icons
        navMenu = findViewById(R.id.nav_menu);
        navOnlineOrdering = findViewById(R.id.nav_online_ordering);
        navReservation = findViewById(R.id.nav_reservation);
        navContact = findViewById(R.id.nav_contact);
        navAbout = findViewById(R.id.nav_about);

        // Set click listeners for sections
        sectionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMenu();
            }
        });

        sectionOnlineOrdering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToOnlineOrdering();
            }
        });

        sectionReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToReservation();
            }
        });

        sectionContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToContact();
            }
        });

        sectionAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAbout();
            }
        });

        // Set click listeners for bottom navigation icons
        navMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMenu();
            }
        });

        navOnlineOrdering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToOnlineOrdering();
            }
        });

        navReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToReservation();
            }
        });

        navContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToContact();
            }
        });

        navAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAbout();
            }
        });
    }
}
