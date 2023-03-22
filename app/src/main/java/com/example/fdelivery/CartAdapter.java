package com.example.fdelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fdelivery.db.AppDb;
import com.example.fdelivery.db.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    Context context;
    List<CartItem> cartItems;
     private onClickListener onClickListener;

    public CartAdapter(Context context) {
        this.context = context;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CartItem cartItem=cartItems.get(position);
        holder.nameView.setText(cartItem.name);
        holder.priceView.setText("$"+cartItem.price);
        Glide.with(holder.itemView.getContext()).load(cartItem.image).into(holder.imageView);
        holder.itemCount.setText(String.valueOf(cartItem.count));
        holder.tPriceView.setText("$"+Math.round(cartItem.count*cartItem.price));

        holder.btnMin.setOnClickListener(view -> {
            if(onClickListener!=null){
                onClickListener.onClick("min",cartItem);
            }
//            if(cartItem.count>1){
//                AppDb db=AppDb.getInstance(context);
//                CartItem cartItem1=new CartItem();
//                cartItem1.name=cartItem.name;
//                cartItem1.price=cartItem.price;
//                cartItem1.image=cartItem.image;
//                cartItem1.uid=cartItem.uid;
//                cartItem1.count=cartItem.count-1;
//                db.cartItemDao().updateCartItem(cartItem1);
//                notifyDataSetChanged();
//            }
//            holder.itemCount.setText(String.valueOf(cartItem.count));
//            holder.tPriceView.setText("$"+Math.round(cartItem.count*cartItem.price));
        });
        holder.btnPlus.setOnClickListener(view -> {
            if(onClickListener!=null){
                onClickListener.onClick("add",cartItem);
            }
//            AppDb db=AppDb.getInstance(context);
//            CartItem cartItem1=new CartItem();
//            cartItem1.name=cartItem.name;
//            cartItem1.price=cartItem.price;
//            cartItem1.image=cartItem.image;
//            cartItem1.uid=cartItem.uid;
//            cartItem1.count=cartItem.count+1;
//            db.cartItemDao().updateCartItem(cartItem1);
//            notifyDataSetChanged();
//
//            holder.itemCount.setText(String.valueOf(cartItem.count));
//            holder.tPriceView.setText("$"+Math.round(cartItem.count*cartItem.price));
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public void setOnClickListener(CartAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface onClickListener{
        void onClick(String btn,CartItem cartItem);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,btnMin,btnPlus;
        TextView nameView,priceView,tPriceView,itemCount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.itemImg);
            nameView=itemView.findViewById(R.id.name);
            priceView=itemView.findViewById(R.id.price);
            tPriceView=itemView.findViewById(R.id.tPrice);
            btnMin=itemView.findViewById(R.id.btnMin);
            itemCount=itemView.findViewById(R.id.itemCount);
            btnPlus=itemView.findViewById(R.id.btnPlus);
        }
    }
}
