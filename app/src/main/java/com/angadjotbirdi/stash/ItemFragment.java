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

/**
 * Created by root on 10/24/16.
 */

public class ItemFragment extends Fragment {

    private static final String ARG_ITEM_ID = "item_id";
    private static final String TAG = "ItemFragment";

    private Item item;
    private EditText nameTextField;
    private EditText priceTextField;
    private Button saveButton;

    private String tempName;
    private String tempPrice;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        int itemID = (int) getArguments().getSerializable(ARG_ITEM_ID);
        item = ItemLab.get(getActivity()).getItem(itemID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_item, container, false);

        nameTextField = (EditText)v.findViewById(R.id.item_name);
        nameTextField.setText(item.getName());
        nameTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tempName = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tempName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        priceTextField = (EditText)v.findViewById(R.id.item_price);
        String priceText = item.getPrice() + "";
        priceTextField.setText(priceText);

        priceTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tempPrice = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tempPrice = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Set the tempName and tempPrice to the text that is displayed when the view is created
        //so if the user presses the save button without entering anything, it won't cause a
        //null pointer exception
        tempName = item.getName();
        tempPrice = item.getPrice() + "";

        saveButton = (Button)v.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempName.equals("")){
                    //TODO:Use popup window to reprompt user to enter name and reset fields
                }

                else{
                    item.setName(tempName);
                }

                if(tempPrice.matches("[0-9]+") || tempPrice.equals("")){
                    item.setPrice(Integer.parseInt(tempPrice));
                }

                else{
                    //TODO:Use popup window to reprompt user to enter price and reset fields


                }
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
