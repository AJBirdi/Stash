package com.angadjotbirdi.stash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 10/24/16.
 */

public class ItemListFragment extends Fragment {

    private static final String TAG = "ItemListFragment";

    private RecyclerView itemRecyclerView;
    private ItemAdapter itemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_item_view, container, false);

        itemRecyclerView = (RecyclerView) view.findViewById(R.id.item_recycler_view);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        ItemLab itemLab = ItemLab.get(getActivity());
        List<Item> items = itemLab.getItems();

        if(itemAdapter == null){
            itemAdapter = new ItemAdapter(items);
            itemRecyclerView.setAdapter(itemAdapter);
        }
        else {
            itemAdapter.notifyDataSetChanged();
        }

    }

    private class ItemHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

        private TextView nameTextView;
        private TextView priceTextView;
        private TextView idTextView;

        private Item item;

        @Override
        public void onClick(View v){
            Intent intent = ItemActivity.newIntent(getActivity(), item.getId());
            startActivity(intent);
        }

        public void bindItem(Item item){
            this.item = item;

            String priceText = "Price :: " + item.getPrice();

            nameTextView.setText(item.getName());
            priceTextView.setText(priceText);
        }

        public ItemHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            nameTextView = (TextView) itemView.findViewById(R.id.item_name_text_view);
            priceTextView = (TextView) itemView.findViewById(R.id.item_price_text_view);
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder>{

        private List<Item> items;

        public ItemAdapter(List<Item> items){
            this.items = items;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            View view = layoutInflater.inflate(R.layout.list_item_item, parent, false);

            return new ItemHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            Item item = items.get(position);
            holder.bindItem(item);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

}
