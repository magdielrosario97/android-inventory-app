package com.snhu.inventoryapp;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SmsPermissionActivity extends AppCompatActivity {
    private static final int SMS_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sms_permission);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.SmsPermissionLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Connect buttons to XML elements by ID
        Button enableSmsButton = findViewById(R.id.EnableSmsButton);
        Button skipButton = findViewById(R.id.SkipButton);

        // Enable SMS
        enableSmsButton.setOnClickListener(view -> {
            // Check if SEND_SMS permission
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                // Ask user for permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
            } else {
                // Create toast to notify permissions are already enabled and return to inventory
                Toast.makeText(this, "SMS permissions already enabled", Toast.LENGTH_SHORT).show();
                goBackToInventory();
            }
        });

        // Skip button
        skipButton.setOnClickListener(view -> goBackToInventory());
    }

    // Handle results of permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Check that SMS permission was granted
        if (requestCode == SMS_PERMISSION_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Create toast to notify permissions are enabled and return to inventory
            Toast.makeText(this, "SMS permissions enabled", Toast.LENGTH_SHORT).show();
            goBackToInventory();
        }
    }

    private void goBackToInventory() {
        Intent intent = new Intent(SmsPermissionActivity.this, InventoryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}