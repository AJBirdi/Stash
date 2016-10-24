package com.angadjotbirdi.stash;

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

    private void updateUI(){
        ItemLab itemLab = ItemLab.get(getActivity());
        List<Item> items = itemLab.getItems();

        itemAdapter = new ItemAdapter(items);
        itemRecyclerView.setAdapter(itemAdapter);
    }

    private class ItemHolder extends RecyclerView.ViewHolder{

        public TextView nameTextView;

        public ItemHolder(View itemView){
            super(itemView);

            nameTextView = (TextView) itemView;
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

            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);

            return new ItemHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            Item item = items.get(position);
            holder.nameTextView.setText(item.getName());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

}
