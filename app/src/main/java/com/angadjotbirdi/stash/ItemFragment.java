package com.angadjotbirdi.stash;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static java.lang.Integer.parseInt;

/**
 * Created by root on 10/24/16.
 */

public class ItemFragment extends Fragment {

    private Item item;
    private EditText nameTitleField;
    private EditText priceTitleField;
    private Button saveButton;

    private String tempName;
    private String tempPrice;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        item = new Item();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_item, container, false);

        nameTitleField = (EditText)v.findViewById(R.id.item_name);
        nameTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tempName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        priceTitleField = (EditText)v.findViewById(R.id.item_price);
        priceTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tempPrice = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        saveButton = (Button)v.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempName.equals("")){
                    //Use popup window to reprompt user to enter name and reset fields
                }
                else{
                    item.setName(tempName);
                }

                if(tempPrice.matches(".*\\d+.*") || tempPrice.equals("")){
                    //Use popup window to reprompt user to enter price and reset fields
                }
                else{
                    item.setPrice(Integer.parseInt(tempPrice));
                }
            }
        });

        return v;
    }
}
