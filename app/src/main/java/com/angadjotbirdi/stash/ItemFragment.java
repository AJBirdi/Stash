package com.angadjotbirdi.stash;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by root on 10/24/16.
 */

//TODO: Fix bug where null and empty items are being saved and non-null items are not being saved

public class ItemFragment extends Fragment {

    private static final String ARG_ITEM_ID = "item_id";
    private static final String TAG = "ItemFragment";

    private Item item;

    private EditText nameTextField;
    private EditText priceTextField;
    private Button saveButton;

    private String tempName;
    private String tempPrice;
    private String nameText;
    private String priceText;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        int itemID = (int) getArguments().getSerializable(ARG_ITEM_ID);
        item = ItemLab.get(getActivity()).getItem(itemID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_item, container, false);

        //tempName = item.getName();
        //tempPrice = item.getPrice() + "";

        if(item == null){
            item = new Item();
            nameText = "";
            priceText = "";
            Log.d(TAG, "IT worked!");
        }
        else if(item != null){
            nameText = item.getName();
            priceText = item.getPrice() + "";
        }

        nameTextField = (EditText)v.findViewById(R.id.item_name);
        nameTextField.setText(nameText);
        nameTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //nameText = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nameText = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        priceTextField = (EditText)v.findViewById(R.id.item_price);
        priceTextField.setText(priceText);
        priceTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //priceText= s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                priceText = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        saveButton = (Button)v.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameText.equals("") || nameText == null){
                    //TODO:Use popup window to reprompt user to enter name and reset fields
                }

                else{
                    Log.d(TAG, nameText + " is the name");
                    item.setName(nameText);
                }

                if(priceText.matches("[0-9]+")){
                    item.setPrice(Integer.parseInt(priceText));
                }

                else{
                    //TODO:Use popup window to reprompt user to enter price and reset fields
                }

                getActivity().finish();
            }
        });

        return v;
    }

    public static ItemFragment newInstance(int itemId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM_ID, itemId);

        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
