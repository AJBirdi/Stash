package com.angadjotbirdi.stash;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InventoryDbHelper extends SQLiteOpenHelper{


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Inventory.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + InventoryContract.Inventory.TABLE_NAME + " (" +
                    InventoryContract.Inventory._ID + " INTEGER PRIMARY KEY," +
                    InventoryContract.Inventory.COLUMN_NAME_NAME + " TEXT," +
                    InventoryContract.Inventory.COLUMN_NAME_PRICE + " INTEGER ][;" + " );";

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

    public boolean insertItem(String name, int price){
        SQLiteDatabase write_db =  getWritableDatabase();
        ContentValues write_values = new ContentValues();

        write_values.put(InventoryContract.Inventory.COLUMN_NAME_NAME, name);
        write_values.put(InventoryContract.Inventory.COLUMN_NAME_PRICE, price);

        write_db.insert(InventoryContract.Inventory.TABLE_NAME, null, write_values);

        return true;
    }

    public boolean updateItem(Integer id, String name, int price){
        SQLiteDatabase write_db = getWritableDatabase();
        ContentValues write_values = new ContentValues();

        write_values.put(InventoryContract.Inventory.COLUMN_NAME_NAME, name);
        write_values.put(InventoryContract.Inventory.COLUMN_NAME_PRICE, price);

        write_db.update(InventoryContract.Inventory.TABLE_NAME, write_values, InventoryContract.Inventory._ID + " = ? ", new String[]{ Integer.toString(id)});

        return true;
    }

    public Cursor getItem(int id){
        SQLiteDatabase read_db = getReadableDatabase();

        Cursor singleItem = read_db.rawQuery("SELECT * FROM " + InventoryContract.Inventory.TABLE_NAME + " WHERE" + InventoryContract.Inventory._ID + "=?",
                new String[] {Integer.toString(id)});

        return singleItem;
    }

    public Cursor getAllItems(){
        SQLiteDatabase read_db = getReadableDatabase();

        Cursor allItems = read_db.rawQuery("SELECT * FROM " + InventoryContract.Inventory.TABLE_NAME, null);

        return allItems;
    }

    public Integer deleteItem(Integer id){
        SQLiteDatabase write_db = getWritableDatabase();

        return write_db.delete(InventoryContract.Inventory.TABLE_NAME,
                InventoryContract.Inventory._ID + " = ? ",
                new String[] {Integer.toString(id)});
    }


}
