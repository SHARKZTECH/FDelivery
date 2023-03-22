package com.example.fdelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    RecyclerView popular,category;
    ArrayList<Item> items;
    MyAdapter myAdapter;
    ArrayList<Cat> cats;
    CatAdapter catAdapter;
    FloatingActionButton btnCart;
    LinearLayout btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        items=new ArrayList<>();
        cats=new ArrayList<>();

        popular=findViewById(R.id.recyclerPop);
        category=findViewById(R.id.recyclerCat);
        btnCart=findViewById(R.id.btnCart);
        btnHome=findViewById(R.id.btnHome);

        btnHome.setOnClickListener(view -> {
            startActivity(new Intent(this,HomeActivity.class));
        });

        btnCart.setOnClickListener(view -> {
            Intent i=new Intent(this,CartActivity.class);
            startActivity(i);
        });



        myAdapter=new MyAdapter(this,items);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        popular.setLayoutManager(linearLayoutManager);
        popular.setAdapter(myAdapter);

        catAdapter=new CatAdapter(this,cats);
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        category.setLayoutManager(linearLayoutManager1);
        category.setAdapter(catAdapter);

        getItems();
    }
    private void getItems(){
        items.add(new Item("Pepperoni Pizza",8.78,R.drawable.pop_1));
        items.add(new Item("Cheese Burger",9.87,R.drawable.pop_2));
        items.add(new Item("Vegetable Pizza",8.5,R.drawable.pop_3));

        cats.add(new Cat("Pizza",R.drawable.cat_1));
        cats.add(new Cat("Burger",R.drawable.cat_2));
        cats.add(new Cat("HotDog",R.drawable.cat_3));
        cats.add(new Cat("Drink",R.drawable.cat_4));
        cats.add(new Cat("Donut",R.drawable.cat_5));
    }
}