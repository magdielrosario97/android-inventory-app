package com.snhu.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ItemDao {
    private SQLiteDatabase db;

    public ItemDao(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // CREATE
    public boolean addItem(Item item) {
        // Create set of values
        ContentValues values = new ContentValues();
        values.put("imageId", item.getImageId());
        values.put("name", item.getName());
        values.put("stock", item.getStock());
        values.put("sku", item.getSku().toUpperCase());

        // Insert into DB
        long result = db.insert("Inventory", null, values);

        // Return true if successful
        return result != -1;
    }

    // READ
    public List<Item> getAllItems() {
        // Create list to hold items
        List<Item> itemList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM Inventory", null);

        // If cursor returns items
        if (cursor.moveToFirst()) {
            // Loop through each
            do {
                // Read values
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                int imageId = cursor.getInt(cursor.getColumnIndexOrThrow("imageId"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int stock = cursor.getInt(cursor.getColumnIndexOrThrow("stock"));
                String sku = cursor.getString(cursor.getColumnIndexOrThrow("sku"));

                // Create new item with values
                Item item = new Item(id, imageId, name, stock, sku);

                // Add to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();

        // Return item list
        return itemList;
    }

    // UPDATE
    public boolean updateItem(Item item) {
        // Create set of values
        ContentValues values = new ContentValues();
        values.put("imageId", item.getImageId());
        values.put("name", item.getName());
        values.put("stock", item.getStock());
        values.put("sku", item.getSku().toUpperCase());

        // Update the item from Inventory by ID
        int rowsAffected = db.update("Inventory", values, "id = ?", new String[]{String.valueOf(item.getId())});

        // Return true if at least one row is updated
        return rowsAffected > 0;
    }

    // DELETE
    public boolean deleteItem(int id) {
        // Delete item from Inventory by ID
        int rowsDeleted = db.delete("Inventory", "id = ?", new String[]{String.valueOf(id)});

        // Return true if at least one row was deleted
        return rowsDeleted > 0;
    }
}
