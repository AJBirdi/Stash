package com.angadjotbirdi.stash;

import android.provider.BaseColumns;

public class InventoryContract {

    private InventoryContract(){}

    public static class Inventory implements BaseColumns{

        public static final String TABLE_NAME = "inventory";

        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PRICE = "price";

    }
}
