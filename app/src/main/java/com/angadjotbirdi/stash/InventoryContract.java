package com.angadjotbirdi.stash;

import android.provider.BaseColumns;

class InventoryContract {

    private InventoryContract(){}

    static class Inventory implements BaseColumns{

        static final String TABLE_NAME = "inventory";

        static final String COLUMN_NAME_NAME = "name";
        static final String COLUMN_NAME_PRICE = "price";

    }
}
