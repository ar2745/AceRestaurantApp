package com.example.acerestaurantapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ReservationActivity extends AppCompatActivity {

    private Button buttonTime, buttonPartySize, buttonDate, buttonSubmitReservation;
    private EditText inputFirstName, inputLastName, inputEmail, inputPhone, inputAdditionalDetails;
    private String selectedTime, selectedDate;
    private int selectedPartySize;

    // Method to show TimePickerDialog
    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                buttonTime.setText(selectedTime);
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    // Method to show AlertDialog for selecting party size
    private void showPartySizePicker() {
        final android.app.AlertDialog.Builder d = new android.app.AlertDialog.Builder(ReservationActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_party_size_picker, null);
        d.setTitle("Select Party Size");
        d.setView(dialogView);

        final android.widget.NumberPicker numberPicker = dialogView.findViewById(R.id.number_picker);
        numberPicker.setMaxValue(20);
        numberPicker.setMinValue(1);
        numberPicker.setValue(1);

        d.setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                selectedPartySize = numberPicker.getValue();
                buttonPartySize.setText(String.valueOf(selectedPartySize));
            }
        });
        d.setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        d.show();
    }

    // Method to show DatePickerDialog
    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectedDate = String.format("%04d-%02d-%02d", year, monthOfYear + 1, dayOfMonth);
                buttonDate.setText(selectedDate);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    // Method to validate inputs and submit reservation
    private void submitReservation() {
        String firstName = inputFirstName.getText().toString();
        String lastName = inputLastName.getText().toString();
        String email = inputEmail.getText().toString();
        String phone = inputPhone.getText().toString();
        String additionalDetails = inputAdditionalDetails.getText().toString();

        // Validate input fields
        if (!isValidInput(firstName, lastName, email, phone, selectedTime, selectedDate, selectedPartySize)) {
            Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show();
            return;
        }

        // Send reservation request
        boolean isReservationSent = sendReservation(firstName, lastName, email, phone, selectedTime, selectedDate, selectedPartySize, additionalDetails);
        if (isReservationSent) {
            Toast.makeText(this, "Reservation Request Sent!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to send reservation. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to validate input fields
    private boolean isValidInput(String firstName, String lastName, String email, String phone, String time, String date, int partySize) {
        return !firstName.isEmpty() && !lastName.isEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && android.util.Patterns.PHONE.matcher(phone).matches()
                && time != null && date != null && partySize > 0;
    }

    // Placeholder method for sending reservation
    private boolean sendReservation(String firstName, String lastName, String email, String phone, String time, String date, int partySize, String additionalDetails) {
        // Replace with actual logic to send reservation details to a server or database
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        // Initialize UI components
        buttonTime = findViewById(R.id.button_time);
        buttonPartySize = findViewById(R.id.button_party_size);
        buttonDate = findViewById(R.id.button_date);
        buttonSubmitReservation = findViewById(R.id.button_submit_reservation);

        inputFirstName = findViewById(R.id.input_first_name);
        inputLastName = findViewById(R.id.input_last_name);
        inputEmail = findViewById(R.id.input_email);
        inputPhone = findViewById(R.id.input_phone);
        inputAdditionalDetails = findViewById(R.id.input_additional_details);

        // Set click listeners for buttons
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        buttonPartySize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPartySizePicker();
            }
        });

        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        buttonSubmitReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitReservation();
            }
        });
    }
}
