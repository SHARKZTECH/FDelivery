package com.example.fdelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.fdelivery.db.AppDb;
import com.example.fdelivery.db.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    ArrayList<Item> items;
    CartAdapter cartAdapter;
    RecyclerView recyclerView;
    List<CartItem> cartItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        items=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerCart);


        cartAdapter=new CartAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);

        getItems();
        cartAdapter.setCartItems(cartItems);

        cartAdapter.setOnClickListener(new CartAdapter.onClickListener() {
            @Override
            public void onClick(int pos, CartItem cartItem) {
            AppDb db=AppDb.getInstance(getApplicationContext());
            CartItem cartItem1=new CartItem();
            cartItem1.name=cartItem.name;
            cartItem1.price=cartItem.price;
            cartItem1.image=cartItem.image;
            cartItem1.uid=cartItem.uid;
            cartItem1.count=cartItem.count+1;
            db.cartItemDao().updateCartItem(cartItem1);
            cartAdapter.notifyDataSetChanged();

            getItems();
            cartAdapter.setCartItems(cartItems);
            }
        });

    }
    private void getItems() {
        AppDb db=AppDb.getInstance(this.getApplicationContext());
        cartItems= (List<CartItem>) db.cartItemDao().getAllCartItems();
//        cartAdapter.notifyDataSetChanged();

        items.add(new Item("Pepperoni Pizza", 8.78, R.drawable.pop_1));
        items.add(new Item("Cheese Burger", 9.87, R.drawable.pop_2));
        items.add(new Item("Vegetable Pizza", 8.5, R.drawable.pop_3));

    }
    }