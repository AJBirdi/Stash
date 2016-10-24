package com.angadjotbirdi.stash;

import android.support.v4.app.Fragment;

/**
 * Created by root on 10/24/16.
 */

public class ItemListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new ItemListFragment();
    }
}
