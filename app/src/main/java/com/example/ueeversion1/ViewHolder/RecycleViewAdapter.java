package com.example.ueeversion1.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ueeversion1.Model.Item;
import com.example.ueeversion1.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private Context context;
    private List<Item> categories;
    private String[] newReception;

    public RecycleViewAdapter(Context context, ArrayList<Item> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sub_category_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.MyViewHolder holder, int position) {
        String receptions = categories.get(position).getReceptions();
        String[] newReceptions = receptions.split(" ",3);
        for (String a : newReceptions){
            holder.textView.setText(a);
        }

    }
    @Override
    public int getItemCount() {
        return categories.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);

            textView=(TextView) itemView.findViewById(R.id.textViewSubCategory);
        }
    }
}
