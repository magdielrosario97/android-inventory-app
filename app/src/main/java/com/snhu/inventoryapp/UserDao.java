package com.snhu.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
    private SQLiteDatabase db;

    public UserDao(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // Register new user with email and password
    public boolean registerUser(String email, String password) {
        // Create values variable to store data
        ContentValues values = new ContentValues();

        // Add email and password to the values
        values.put("email", email);
        values.put("password", password);

        // Insert values into the Users table
        long result = db.insert("Users", null, values);

        // If insert returns -1, it failed
        return result != -1;
    }

    // Validate user login
    public boolean checkLogin(String email, String password) {
        // Query the Users table for a row with matching email and password
        Cursor cursor = db.rawQuery(
                "SELECT * FROM Users WHERE email = ? AND password = ?",
                new String[]{email, password}
        );

        // If match is found, login successful
        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }
}
