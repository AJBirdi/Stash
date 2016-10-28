package com.angadjotbirdi.stash;

import android.content.Context;
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
    private List<Item> items;

    private SQLiteDatabase db;

    public static ItemLab get(Context context){
        if(itemLab == null){
            itemLab = new ItemLab(context);
        }
        return itemLab;
    }

    private ItemLab(Context context){
        //db = new InventoryDbHelper(context.getApplicationContext()).getWritableDatabase();

        items = new ArrayList<>();
    }

    public void addItem(Item item){

        Log.d(TAG, "Beginning of add method");

        String nameTest = item.getName();

        if(nameTest != null) {
            items.add(item);
            Log.d(TAG, "I added an item");
        }
        else if(nameTest == null){
            Log.d(TAG, "I'm null");
        }
        Log.d(TAG, "End of add method");

    }

    public List<Item> getItems(){
        return items;
    }

    public Item getItem(int id){
        for(Item item : items){
            if(item.getId() == (id)){
                return item;
            }
        }
        return null;
    }
}