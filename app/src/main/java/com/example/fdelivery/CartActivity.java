package com.example.fdelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;
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
    TextView tItems,delivery,tax,total;
    int taxV=0;
    int totalItem=0;
    LinearLayout btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        items=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerCart);
        tItems=findViewById(R.id.totalItemsValue);
        delivery=findViewById(R.id.deliveryValue);
        tax=findViewById(R.id.taxValue);
        total=findViewById(R.id.totalValue);
        btnHome=findViewById(R.id.btnHome);

        btnHome.setOnClickListener(view -> {
            startActivity(new Intent(this,HomeActivity.class));
        });

        cartAdapter=new CartAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);

        getItems();
        cartAdapter.setCartItems(cartItems);

        cartAdapter.setOnClickListener(new CartAdapter.onClickListener() {
            @Override
            public void onClick(String btn, CartItem cartItem) {
            AppDb db=AppDb.getInstance(getApplicationContext());
            CartItem cartItem1=new CartItem();
            if(btn.equals("add")){
                cartItem1.name=cartItem.name;
                cartItem1.price=cartItem.price;
                cartItem1.image=cartItem.image;
                cartItem1.uid=cartItem.uid;
                cartItem1.count=cartItem.count+1;
                db.cartItemDao().updateCartItem(cartItem1);
                cartAdapter.notifyDataSetChanged();
            }else {
                if(cartItem.count>1){
                    cartItem1.name=cartItem.name;
                    cartItem1.price=cartItem.price;
                    cartItem1.image=cartItem.image;
                    cartItem1.uid=cartItem.uid;
                    cartItem1.count=cartItem.count-1;
                    db.cartItemDao().updateCartItem(cartItem1);
                    cartAdapter.notifyDataSetChanged();
                }
            }
            cartItems.clear();
             getItems();
            cartAdapter.setCartItems(cartItems);
            setValues();
            }
        });

     setValues();
    }
    private void setValues(){
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                taxV=0;
                totalItem=0;
                for (CartItem cartItem:cartItems){
                    taxV+= (int) Math.round(cartItem.price*cartItem.count*.10);
                    totalItem+=Math.round(cartItem.price*cartItem.count);
                }
                tax.setText("$"+taxV);
                tItems.setText("$"+totalItem);
                total.setText("$"+(taxV+totalItem+10));
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