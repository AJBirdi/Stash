package com.angadjotbirdi.stash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity" ;
    public static final String EXTRA_MESSAGE = "com.angadjotbirdi.stash.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void editItems(View view){
        Intent intent = new Intent(this, ItemViewActivity.class);
        String message = "I made it!";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
