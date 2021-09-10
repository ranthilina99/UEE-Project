package com.example.ueeversion1;

import android.widget.Filter;

import com.example.ueeversion1.Model.Item;
import com.example.ueeversion1.ViewHolder.AdminViewHolder;

import java.util.ArrayList;

public class FilterProductAdmin extends Filter {
    private AdminViewHolder holder;
    private ArrayList<Item> filterListAdmin;

    public FilterProductAdmin(AdminViewHolder holder, ArrayList<Item> filterListAdmin) {
        this.holder = holder;
        this.filterListAdmin = filterListAdmin;
    }

    @Override
    protected Filter.FilterResults performFiltering(CharSequence constraint) {
        Filter.FilterResults results=new Filter.FilterResults();
        if(constraint !=null && constraint.length()>0){
            constraint=constraint.toString().toUpperCase();
            ArrayList<Item> filterProducts =new ArrayList<>();
            for(int i=0;i<filterListAdmin.size();i++){
                if(filterListAdmin.get(i).getProductName().toUpperCase().contains(constraint)||
                        filterListAdmin.get(i).getPrice().toUpperCase().contains(constraint)){
                    filterProducts.add(filterListAdmin.get(i));
                }
            }
            results.count=filterProducts.size();
            results.values=filterProducts;
        }else {
            results.count=filterListAdmin.size();
            results.values=filterListAdmin;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {

        holder.productListAdmin=(ArrayList<Item>)results.values;
        holder.notifyDataSetChanged();
    }
}
