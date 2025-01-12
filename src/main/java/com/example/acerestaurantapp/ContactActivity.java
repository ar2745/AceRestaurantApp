package com.example.acerestaurantapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    // UI elements declaration
    private EditText inputFirstName, inputLastName, inputEmail, inputPhone, inputMessage;
    private Button buttonSubmitContact;

    // Method to handle submission of contact form
    private void submitContact() {
        // Retrieve input values
        String firstName = inputFirstName.getText().toString();
        String lastName = inputLastName.getText().toString();
        String email = inputEmail.getText().toString();
        String phone = inputPhone.getText().toString();
        String message = inputMessage.getText().toString();

        // Validate inputs
        if (!isValidInput(firstName, lastName, email, phone, message)) {
            // Display toast if any field is empty
            Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show();
            return;
        }

        // Send contact message (dummy implementation)
        boolean isContactSent = sendContact(firstName, lastName, email, phone, message);
        if (isContactSent) {
            // Display success message
            Toast.makeText(this, "Message Sent!", Toast.LENGTH_SHORT).show();
        } else {
            // Display failure message
            Toast.makeText(this, "Failed to send message. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    // Placeholder method for sending contact message (replace with actual implementation)
    private boolean sendContact(String firstName, String lastName, String email, String phone, String message) {
        // Placeholder: Replace with actual logic to send contact message
        return true; // Dummy return indicating success
    }

    // Method to validate input fields
    private boolean isValidInput(String firstName, String lastName, String email, String phone, String message) {
        return !firstName.isEmpty() && !lastName.isEmpty() &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                android.util.Patterns.PHONE.matcher(phone).matches() &&
                !message.isEmpty();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Initialize UI components
        inputFirstName = findViewById(R.id.input_first_name);
        inputLastName = findViewById(R.id.input_last_name);
        inputEmail = findViewById(R.id.input_email);
        inputPhone = findViewById(R.id.input_phone);
        inputMessage = findViewById(R.id.input_message);

        buttonSubmitContact = findViewById(R.id.button_submit_contact);

        // Set button click listener
        buttonSubmitContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitContact(); // Call method to handle button click
            }
        });
    }
}
