package com.angadjotbirdi.stash;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class ItemActivity extends SingleFragmentActivity {

    private static final String EXTRA_ITEM_ID =
            "com.angadjotbirdi.stash.item_id";

    public static Intent newIntent(Context packageContext, int itemID){
        Intent intent = new Intent(packageContext, ItemActivity.class);
        intent.putExtra(EXTRA_ITEM_ID, itemID);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        int itemID = (int) getIntent().getSerializableExtra(EXTRA_ITEM_ID);
        return ItemFragment.newInstance(itemID);
    }
}
