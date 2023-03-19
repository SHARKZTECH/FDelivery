package com.example.fdelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    RecyclerView popular;
    ArrayList<Item> items;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        items=new ArrayList<>();
        popular=findViewById(R.id.recyclerPop);


        myAdapter=new MyAdapter(this,items);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        popular.setLayoutManager(linearLayoutManager);
        popular.setAdapter(myAdapter);

        getItems();
    }
    private void getItems(){
        items.add(new Item("Pepperoni Pizza",8.78,R.drawable.pop_1));
        items.add(new Item("Cheese Burger",9.87,R.drawable.pop_2));
        items.add(new Item("Vegetable Pizza",8.5,R.drawable.pop_3));
    }
}