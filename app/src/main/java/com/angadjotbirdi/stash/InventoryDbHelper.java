package com.angadjotbirdi.stash;

import android.content.Context;
import android.database.ContentObservable;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InventoryDbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Inventory.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + InventoryContract.Inventory.TABLE_NAME + " (" +
                    InventoryContract.Inventory._ID + " INTEGER PRIMARY KEY," +
                    InventoryContract.Inventory.COLUMN_NAME_NAME + " TEXT," +
                    InventoryContract.Inventory.COLUMN_NAME_PRICE + "INTEGER" + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + InventoryContract.Inventory.TABLE_NAME;

    public InventoryDbHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }


}
