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
        for (int i = 0; i < 100; i++){
            Item item = new Item();
            item.setName("Item # " + i);
            item.setPrice(i);
            item.setId(i * 5);
            items.add(item);
        }
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