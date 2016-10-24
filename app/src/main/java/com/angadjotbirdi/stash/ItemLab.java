package com.angadjotbirdi.stash;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 10/24/16.
 */

public class ItemLab{

    private static ItemLab itemLab;

    private SQLiteDatabase db;

    public static ItemLab get(Context context){
        if(itemLab == null){
            itemLab = new ItemLab(context);
        }
        return itemLab;
    }

    private ItemLab(Context context){
        db = new InventoryDbHelper(context.getApplicationContext()).getWritableDatabase();
    }

    public List<Item> getItems(){
        return new ArrayList<>();
    }

    public Item getItem(int id){
        return null;
    }


}