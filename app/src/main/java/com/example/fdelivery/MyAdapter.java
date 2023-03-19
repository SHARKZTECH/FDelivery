package com.example.fdelivery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Item> items;

    public MyAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      Item item=items.get(position);

      holder.nameView.setText(item.getName());
      holder.priceView.setText("$"+item.getPrice());
      Glide.with(holder.imageView.getContext()).load(item.getImage()).into(holder.imageView);

      holder.addBtn.setOnClickListener(view -> {
          Intent intent=new Intent(context,ItemActivity.class);
          context.startActivity(intent);
      });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameView,priceView,addBtn;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView=itemView.findViewById(R.id.name);
            priceView=itemView.findViewById(R.id.price);
            imageView=itemView.findViewById(R.id.itemImg);
            addBtn=itemView.findViewById(R.id.btnAdd);
        }
    }
}
