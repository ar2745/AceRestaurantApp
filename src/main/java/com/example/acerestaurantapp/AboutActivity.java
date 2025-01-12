package com.example.acerestaurantapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Set up text views with restaurant information
        TextView aboutDescription = findViewById(R.id.aboutDescription);
        aboutDescription.setText(getString(R.string.about_description));

        TextView missionStatement = findViewById(R.id.missionStatement);
        missionStatement.setText(getString(R.string.mission_statement));

        TextView contactInfo = findViewById(R.id.contactInfo);
        contactInfo.setText(getString(R.string.contact_info));
    }
}
