package com.angadjotbirdi.stash;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class ItemActivity extends SingleFragmentActivity {

    private static final String EXTRA_ITEM_NAME =
            "com.angadjotbirdi.stash.item_id";

    public static Intent newIntent(Context packageContext, String itemName){
        Intent intent = new Intent(packageContext, ItemActivity.class);
        intent.putExtra(EXTRA_ITEM_NAME, itemName);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        String itemName = (String) getIntent().getSerializableExtra(EXTRA_ITEM_NAME);
        return ItemFragment.newInstance(itemName);
    }
}
