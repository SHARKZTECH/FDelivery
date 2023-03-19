package com.example.fdelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.MyViewHolder> {
    Context context;
    ArrayList<Cat> cats;

    public CatAdapter(Context context, ArrayList<Cat> cats) {
        this.context = context;
        this.cats = cats;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cat cat=cats.get(position);
        switch (position){
            case 0:
                holder.linearLayout.setBackground(context.getDrawable(R.drawable.cat1_bg));
            case 1:
                holder.linearLayout.setBackground(context.getDrawable(R.drawable.cat2_bg));
            case 2:
                holder.linearLayout.setBackground(context.getDrawable(R.drawable.cat3_bg));
            case 3:
                holder.linearLayout.setBackground(context.getDrawable(R.drawable.cat4_bg));
            case 4:
                holder.linearLayout.setBackground(context.getDrawable(R.drawable.cat5_bg));
        }
        holder.textView.setText(cat.getName());
        Glide.with(holder.itemView.getContext()).load(cat.getImg()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.name);
            imageView=itemView.findViewById(R.id.itemImg);
            linearLayout=itemView.findViewById(R.id.linear);
        }
    }
}
