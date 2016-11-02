package com.angadjotbirdi.stash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 10/24/16.
 */

public class ItemLab{

    private static final String TAG = "ItemLab";

    private static ItemLab itemLab;
    private Context context;
    private SQLiteDatabase database;

    private SQLiteDatabase db;

    public static ItemLab get(Context context){
        if(itemLab == null){
            itemLab = new ItemLab(context);
        }
        return itemLab;
    }

    private ItemLab(Context context){
        this.context = context.getApplicationContext();
        database = new InventoryDbHelper(this.context).getWritableDatabase();
    }

    public void addItem(Item item){
        ContentValues values = getContentValues(item);

        Log.d(TAG, values.toString());

        database.insert(InventoryContract.Inventory.TABLE_NAME, null, values);
    }

    public List<Item> getItems(){
        List<Item> items = new ArrayList<>();

        ItemCursorWrapper cursorWrapper = queryItems(null, null);

        try {
            cursorWrapper.moveToFirst();
            while(cursorWrapper.moveToNext()){
                items.add(cursorWrapper.getItem());
                cursorWrapper.moveToNext();
            }
        }
        finally {
            cursorWrapper.close();
        }
        return items;
    }

    public Item getItem(String name){
        ItemCursorWrapper cursorWrapper = queryItems(
                InventoryContract.Inventory.COLUMN_NAME_NAME + " = ?",
                new String[] {name}
        );

        try{
            if (cursorWrapper.getCount() == 0){
                return null;
            }

            cursorWrapper.moveToFirst();
            return cursorWrapper.getItem();
        }
        finally {
            cursorWrapper.close();
        }
    }

    public void updateItem(Item item){
        String nameString = item.getName();
        ContentValues values = getContentValues(item);

        database.update(InventoryContract.Inventory.TABLE_NAME, values,
                InventoryContract.Inventory.COLUMN_NAME_NAME + " = ?",
                new String[] {nameString});
    }

    private static ContentValues getContentValues(Item item){
        ContentValues values = new ContentValues();

        values.put(InventoryContract.Inventory.COLUMN_NAME_NAME, item.getName());
        values.put(InventoryContract.Inventory.COLUMN_NAME_PRICE, item.getPrice());

        return values;
    }

    private ItemCursorWrapper queryItems(String whereClause, String[] whereArgs){
        Cursor cursor = database.query(
                InventoryContract.Inventory.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        cursor.close();

        return new ItemCursorWrapper(cursor);
    }
}