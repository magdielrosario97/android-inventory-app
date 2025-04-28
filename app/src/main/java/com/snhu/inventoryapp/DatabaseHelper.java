package com.snhu.inventoryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "InventoryApp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Users table
        String userTable = "CREATE TABLE Users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email TEXT UNIQUE, " +
                "password TEXT)";

        // Create Inventory table
        String inventoryTable = "CREATE TABLE Inventory (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "imageId INTEGER, " +
                "name TEXT, " +
                "stock INTEGER, " +
                "sku TEXT)";

        // Make sure to return to string
        db.execSQL(userTable);
        db.execSQL(inventoryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not used
    }
}
