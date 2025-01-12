package com.example.acerestaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity {

    // Declare UI elements
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private SwitchCompat rememberMeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        rememberMeSwitch = findViewById(R.id.rememberMeSwitch);

        // Set click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input from EditText fields
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                boolean rememberMe = rememberMeSwitch.isChecked();

                // Check if username or password is empty
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    // Show a toast message if either field is empty
                    Toast.makeText(MainActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                } else {
                    // If fields are filled, navigate to HomeActivity
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    // Show a success message
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    // Optional: Save credentials if Remember Me is checked
                    if (rememberMe) {
                        // Save the credentials (this is just a placeholder, implement secure storage)
                        Toast.makeText(MainActivity.this, "Credentials Saved", Toast.LENGTH_SHORT).show();
                    }
                    // Optional: Close the login activity to prevent going back to it
                    finish();
                }
            }
        });
    }
}
