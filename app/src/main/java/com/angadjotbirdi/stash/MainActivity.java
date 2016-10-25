package com.angadjotbirdi.stash;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button editButton = (Button)findViewById(R.id.edit_items_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ItemViewActivity.class);
                startActivity(intent);
            }
        });

        Button viewButton = (Button)findViewById(R.id.view_items_button);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
                startActivity(intent);
            }
        });

        populateDatabase(5000);
    }

     public void populateDatabase(int itemCount){
         InventoryDbHelper dbHelper = new InventoryDbHelper(getApplicationContext());
         SQLiteDatabase db = dbHelper.getWritableDatabase();

         //Empties database everytime it needs to be populated.
         db.execSQL("DELETE FROM " + InventoryContract.Inventory.TABLE_NAME);

         String name;
         int price;

         SecureRandom myRandom = new SecureRandom();

         db.beginTransaction();
         try{
             for(int x = 1; x <= itemCount; x++){
                 price = myRandom.nextInt(100000);

                 name = "item" + x;

                 dbHelper.insertItem(name, price);
             }
             db.setTransactionSuccessful();
         }
         finally {
             db.endTransaction();
         }
     }
}
