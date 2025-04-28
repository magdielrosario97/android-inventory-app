package com.snhu.inventoryapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class InventoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inventory);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.InventoryFrameLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Connect RecyclerView to XML by ID
        recyclerView = findViewById(R.id.InventoryRecyclerView);

        // Create grid layout with 3 columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Load item list from DB
        ItemDao itemDao = new ItemDao(this);
        itemList = itemDao.getAllItems();

        // Set adapter
        ItemAdapter adapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(adapter);

        // Add Item Floating Action Button
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddItem);
        fabAddItem.setOnClickListener(view -> {
            Intent intent = new Intent(InventoryActivity.this, EditItemActivity.class);
            // This flag is used to dynamically change Edit Item screen to Add Item screen
            intent.putExtra("isNewItem", true);
            startActivity(intent);
        });

        // Notification Button
        ImageButton notificationButton = findViewById(R.id.NotificationButton);
        notificationButton.setOnClickListener(view -> {
            // Allows navigation to SMS Notification permissions screen
            Intent intent = new Intent(InventoryActivity.this, SmsPermissionActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Reload item list from database
        ItemDao itemDao = new ItemDao(this);
        itemList = itemDao.getAllItems();

        // Update adapter with new data
        ItemAdapter adapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(adapter);

        // Loop through items and check for low stock items
        for (Item item : itemList) {
            // Check SMS permission before sending
            if (item.getStock() == 0 && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                // CANNOT TEST or does not work?
                SmsManager smsManager = getSystemService(SmsManager.class);
                String message = "ALERT: " + item.getName() + " is out of stock!";
                smsManager.sendTextMessage("+18885550000", null, message, null, null);

                // For Testing: Shows code for sending text has ran
                Toast.makeText(this, item.getName() + " is out of stock. SMS sent.", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}

/*
// Sample data for Project Two
itemList = new ArrayList<>();
itemList.add(new Item(R.drawable.item_placeholder_image, "WXH-300 Wireless Headphones", 50, "123456789"));
itemList.add(new Item(R.drawable.item_placeholder_image, "AX730BT Wireless Gaming Mouse", 23, "789456123"));
itemList.add(new Item(R.drawable.item_placeholder_image, "XH-250 Wired Headphones", 75, "753142869"));
itemList.add(new Item(R.drawable.item_placeholder_image, "AX730 Wired Gaming Mouse", 40, "456123789"));
itemList.add(new Item(R.drawable.item_placeholder_image, "ZT100 Compact Bluetooth Speaker", 30, "321654987"));
itemList.add(new Item(R.drawable.item_placeholder_image, "KM900 RGB Mechanical Keyboard", 18, "654987321"));
itemList.add(new Item(R.drawable.item_placeholder_image, "QTR-110 Webcam 1080p", 66, "159357258"));
itemList.add(new Item(R.drawable.item_placeholder_image, "VLX-400 USB-C Charging Hub", 37, "258456147"));
itemList.add(new Item(R.drawable.item_placeholder_image, "ERX-220 Noise Cancelling Mic", 42, "951753852"));
*/
