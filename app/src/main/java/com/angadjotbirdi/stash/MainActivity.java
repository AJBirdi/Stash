package com.angadjotbirdi.stash;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateDatabase(5000);
    }

    public void editItems(View view){
        Intent intent = new Intent(this, ItemViewActivity.class);
        startActivity(intent);
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
