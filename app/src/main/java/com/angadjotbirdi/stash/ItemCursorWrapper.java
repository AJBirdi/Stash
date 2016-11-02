package com.angadjotbirdi.stash;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by root on 10/31/16.
 */

public class ItemCursorWrapper extends CursorWrapper {

    public ItemCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Item getItem(){
        int idLong = getInt(getColumnIndex(InventoryContract.Inventory._ID));
        String nameString = getString(getColumnIndex(InventoryContract.Inventory.COLUMN_NAME_NAME));
        int priceInt = getInt(getColumnIndex(InventoryContract.Inventory.COLUMN_NAME_PRICE));


        Item item = new Item(nameString);
        item.setPrice(priceInt);
        item.setId(idLong);

        return null;
    }
}
