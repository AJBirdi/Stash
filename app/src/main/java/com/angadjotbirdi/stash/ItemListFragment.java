package com.angadjotbirdi.stash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_item_view, container, false);

        itemRecyclerView = (RecyclerView) view.findViewById(R.id.item_recycler_view);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_item_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_item_new_item:
                Item item = new Item();
                ItemLab.get(getActivity()).addItem(item);
                Intent intent = ItemActivity.newIntent(getActivity(), item.getId());
                startActivity(intent);
                Log.d(TAG, item.getName() + " " + item.getPrice());
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void updateUI() {
        ItemLab itemLab = ItemLab.get(getActivity());
        List<Item> items = itemLab.getItems();

        if (itemAdapter == null) {
            itemAdapter = new ItemAdapter(items);
            itemRecyclerView.setAdapter(itemAdapter);
        } else {
            itemAdapter.notifyDataSetChanged();
        }

    }

    private class ItemHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        private TextView nameTextView;
        private TextView priceTextView;

        private Item item;

        @Override
        public void onClick(View v) {
            Intent intent = ItemActivity.newIntent(getActivity(), item.getId());
            startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v) {
            //TODO: Make popup window that asks if they want to delete
            Log.d(TAG, "I was long clicked!");

            return true;
        }


        public void bindItem(Item item) {
            this.item = item;

            String priceText = "Price :: " + item.getPrice();

            nameTextView.setText(item.getName());
            priceTextView.setText(priceText);
        }

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            nameTextView = (TextView) itemView.findViewById(R.id.item_name_text_view);
            priceTextView = (TextView) itemView.findViewById(R.id.item_price_text_view);
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        private List<Item> items;

        public ItemAdapter(List<Item> items) {
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