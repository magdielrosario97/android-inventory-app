package com.snhu.inventoryapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class EditItemActivity extends AppCompatActivity {
    private int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ItemEditFrameLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Read isNewItem flag
        boolean isNewItem = getIntent().getBooleanExtra("isNewItem", false);

        // Read item ID flag
        itemId = getIntent().getIntExtra("id", -1);

        // Back button closes activity
        ImageButton backButton = findViewById(R.id.BackButton);
        backButton.setOnClickListener(view -> {
            finish();
        });

        // Elements to change between Add Item and Edit Item screen
        TextView pageTitle = findViewById(R.id.PageTitle);
        Button saveButton = findViewById(R.id.ItemSaveButton);
        Button deleteButton = findViewById(R.id.ItemDeleteButton);

        // Connect elements to XML elements by ID
        ImageView itemImageView = findViewById(R.id.ItemImage);
        EditText itemNameEditText = findViewById(R.id.ItemNameEditText);
        EditText itemStockEditText = findViewById(R.id.ItemStockEditText);
        EditText itemSkuEditText = findViewById(R.id.ItemSkuEditText);

        // Get values passed from inventory card
        int imageId = getIntent().getIntExtra("imageId", R.drawable.item_placeholder_image);
        String name = getIntent().getStringExtra("name");
        int stock = getIntent().getIntExtra("stock", -1);
        String sku = getIntent().getStringExtra("sku");

        // Set image and values
        itemImageView.setImageResource(imageId);
        if (name != null) itemNameEditText.setText(name);
        if (stock != -1) itemStockEditText.setText(String.valueOf(stock));
        if (sku != null) itemSkuEditText.setText(sku);

        // Check to change between Add Item and Edit Item
        if (isNewItem) {
            pageTitle.setText(getString(R.string.new_item));
            saveButton.setText(getString(R.string.add_item));
            deleteButton.setEnabled(false);
            deleteButton.setVisibility(View.INVISIBLE);

            // Add item
            saveButton.setOnClickListener(view -> {
                // Get field values and trim white space
                String nameField = itemNameEditText.getText().toString().trim();
                String stockField = itemStockEditText.getText().toString().trim();
                String skuField = itemSkuEditText.getText().toString().trim();

                // Validate fields are not empty
                if (nameField.isEmpty() || stockField.isEmpty() || skuField.isEmpty()) {
                    // Create a toast to prompt user for input
                    Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create ItemDao instance for DB access
                ItemDao itemDao = new ItemDao(this);

                // Convert stock input from text to integer
                int stockValue = Integer.parseInt(stockField);

                // Create new item with field values
                Item newItem = new Item(0, imageId, nameField, stockValue, skuField);

                // Attempt to add new item to DB
                boolean addItemSuccess = itemDao.addItem(newItem);

                if (addItemSuccess) {
                    // Create a toast to notify successfully added new item and close
                    Toast.makeText(this, "New item added!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // Create a toast to notify failed to add new item
                    Toast.makeText(this, "Item could not be added", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Change title, button and enable delete button
            pageTitle.setText(getString(R.string.edit_item));
            saveButton.setText(getString(R.string.save_changes));
            deleteButton.setEnabled(true);
            deleteButton.setVisibility(View.VISIBLE);

            // Save changes
            saveButton.setOnClickListener(view -> {
                // Get updated field values and trim white space
                String updatedName = itemNameEditText.getText().toString().trim();
                String updatedStock = itemStockEditText.getText().toString().trim();
                String updatedSku = itemSkuEditText.getText().toString().trim();

                // Validate fields are not empty
                if (updatedName.isEmpty() || updatedStock.isEmpty() || updatedSku.isEmpty()) {
                    // Create a toast to prompt user for input
                    Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convert to int
                int stockValue = Integer.parseInt(updatedStock);

                // Create updated item with the same itemId
                Item updatedItem = new Item(itemId, imageId, updatedName, stockValue, updatedSku);

                // Access db and save changes
                ItemDao itemDao = new ItemDao(this);
                boolean updateSuccess = itemDao.updateItem(updatedItem);

                if (updateSuccess) {
                    // Create toast to notify changes were saved and close
                    Toast.makeText(this, getString(R.string.toast_changes_saved), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // Create toast to notify changes WERE NOT saved
                    Toast.makeText(this, "Item could not be updated", Toast.LENGTH_SHORT).show();
                }
            });

            // Delete item
            deleteButton.setOnClickListener(view -> {
                // Access db and delete item
                ItemDao itemDao = new ItemDao(this);
                boolean deleteSuccess = itemDao.deleteItem(itemId);

                if (deleteSuccess) {
                    // Create toast to notify successful deletion and close
                    Toast.makeText(this, "Item deleted!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // Create toast and notify item WAS NOT deleted
                    Toast.makeText(this, "Item could not be deleted", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    // Closes current activity and returns
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}