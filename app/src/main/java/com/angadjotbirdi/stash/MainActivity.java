package com.angadjotbirdi.stash;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InventoryDbHelper mDbHelper = new InventoryDbHelper(this);
        SQLiteDatabase writeDb = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(InventoryContract.Inventory.COLUMN_NAME_NAME, "My Title");
        values.put(InventoryContract.Inventory.COLUMN_NAME_PRICE, "50");

        long newRowID = writeDb.insert(InventoryContract.Inventory.TABLE_NAME, null, values);

        SQLiteDatabase readDb = mDbHelper.getReadableDatabase();

        String[] projection = {
                InventoryContract.Inventory._ID,
                InventoryContract.Inventory.COLUMN_NAME_NAME,
                InventoryContract.Inventory.COLUMN_NAME_PRICE
        };

        String selection = InventoryContract.Inventory.COLUMN_NAME_NAME + " = ?";
        String[] selectionArgs = {"My Title"};

        String sortOrder = InventoryContract.Inventory.COLUMN_NAME_PRICE + " DESC";

        Cursor c = readDb.query(
                InventoryContract.Inventory.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        String[] columnNames = c.getColumnNames();

        for(int x = 0; x < c.getColumnCount(); x++){
            Log.d(TAG, columnNames[x]);
        }

    }
}