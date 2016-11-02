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

import java.util.List;

/**
 * Created by root on 10/24/16.
 */

//TODO: Fix bug where null and empty items are being saved and non-null items are not being saved

public class ItemFragment extends Fragment {

    private static final String ARG_ITEM_NAME = "item_id";
    private static final String TAG = "ItemFragment";

    private Item item;

    private EditText nameTextField;
    private EditText priceTextField;
    private Button saveButton;

    private String nameText;
    private String priceText;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        String itemName = (String) getArguments().getSerializable(ARG_ITEM_NAME);

        if(item != null){
            item = ItemLab.get(getActivity()).getItem(itemName);
        }
        else{
            item = new Item();
            item.setName("");
            item.setPrice(0);
        }
    }

    @Override
    public void onPause(){
        super.onPause();

        ItemLab.get(getActivity()).updateItem(item  );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_item, container, false);

        if(item == null){
            item = new Item();
            nameText = "";
            priceText = "";
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
                nameText = s.toString();
            }
        });

        priceTextField = (EditText)v.findViewById(R.id.item_price);

        if(priceText.equals("0")){
            priceText = "";
        }

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
                priceText = s.toString();
            }
        });

        saveButton = (Button)v.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean saveName = false;
                boolean savePrice = false;

                if (nameText.equals("") || nameText == null){
                    //TODO:Use popup window to reprompt user to enter name and reset fields
                }

                else{
                    item.setName(nameText);
                     saveName= true;
                }

                if(priceText.matches("[0-9]+")){
                    item.setPrice(Integer.parseInt(priceText));
                    savePrice = true;
                }

                else{
                    //TODO:Use popup window to reprompt user to enter price and reset fields
                }

                if(saveName && savePrice && item != null){
                    ItemLab.get(getActivity()).addItem(item);
                }

                getActivity().finish();
            }
        });

        return v;
    }

    public static ItemFragment newInstance(String itemName){
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM_NAME, itemName);

        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
