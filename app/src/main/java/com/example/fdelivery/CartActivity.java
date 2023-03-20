package com.example.fdelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    ArrayList<Item> items;
    CartAdapter cartAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        items=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerCart);

        cartAdapter=new CartAdapter(this,items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);

        getItems();
    }
    private void getItems() {
        items.add(new Item("Pepperoni Pizza", 8.78, R.drawable.pop_1));
        items.add(new Item("Cheese Burger", 9.87, R.drawable.pop_2));
        items.add(new Item("Vegetable Pizza", 8.5, R.drawable.pop_3));

        items.add(new Item("Pepperoni Pizza", 8.78, R.drawable.pop_1));
        items.add(new Item("Cheese Burger", 9.87, R.drawable.pop_2));
        items.add(new Item("Vegetable Pizza", 8.5, R.drawable.pop_3));
    }
    }