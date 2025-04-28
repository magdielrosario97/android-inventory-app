package com.snhu.inventoryapp;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText emailField, passwordField;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ItemEditFrameLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Connect fields elements to XML
        emailField = findViewById(R.id.EmailText);
        passwordField = findViewById(R.id.PasswordText);
        Button loginButton = findViewById(R.id.LoginButton);
        Button registerButton = findViewById(R.id.RegisterButton);

        // Create UserDao instance for database access
        userDao = new UserDao(this);

        // Handle login button
        loginButton.setOnClickListener(v -> {
            // Get user input and trim white space
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            // Validate fields are not empty
            if (email.isEmpty() || password.isEmpty()) {
                // Create a toast to prompt user for input
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if login is in database
            boolean loginSuccessful = userDao.checkLogin(email, password);

            if (loginSuccessful) {
                // Create a toast to notify user of successful login and go to Inventory screen
                Toast.makeText(this, "Successful login", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, InventoryActivity.class));
            } else {
                // Create a toast to notify user of invalid credentials
                Toast.makeText(this, "Email and/or password invalid", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle register button
        registerButton.setOnClickListener(v -> {
            // Get field values and trim white space
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            // Validate fields are not empty
            if (email.isEmpty() || password.isEmpty()) {
                // Create a toast to prompt user for input
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Attempt to register user
            boolean registrationSuccess = userDao.registerUser(email, password);

            if (registrationSuccess) {
                // Create a toast to notify user of successful registration
                Toast.makeText(this, "Account registered", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, InventoryActivity.class));
            } else {
                // Create a toast to notify user of invalid credentials
                Toast.makeText(this, "Email already registered", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // For Testing: Allows bypassing login during emulation
    public void goToInventory(View view) {
        Intent intent = new Intent(MainActivity.this, InventoryActivity.class);
        startActivity(intent);
    }
}