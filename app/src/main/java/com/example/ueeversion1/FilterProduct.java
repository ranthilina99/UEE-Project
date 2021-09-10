package com.example.ueeversion1;

import android.widget.Filter;

import com.example.ueeversion1.Model.Item;
import com.example.ueeversion1.ViewHolder.UserViewHolder;

import java.util.ArrayList;

public class FilterProduct extends Filter {
    private UserViewHolder adapter;
    private ArrayList<Item> filterList;

    public FilterProduct(UserViewHolder adapter, ArrayList<Item> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        if(constraint !=null && constraint.length()>0){
            constraint=constraint.toString().toUpperCase();
            ArrayList<Item> filterProducts =new ArrayList<>();
            for(int i=0;i<filterList.size();i++){
                if(filterList.get(i).getProductName().toUpperCase().contains(constraint)||
                        filterList.get(i).getPrice().toUpperCase().contains(constraint)){
                        filterProducts.add(filterList.get(i));
                }
            }
            results.count=filterProducts.size();
            results.values=filterProducts;
        }else {
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.productList=(ArrayList<Item>)results.values;
        adapter.notifyDataSetChanged();
    }
}
