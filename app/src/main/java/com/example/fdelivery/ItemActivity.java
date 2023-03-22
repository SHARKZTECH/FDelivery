package com.example.fdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fdelivery.db.AppDb;
import com.example.fdelivery.db.CartItem;

public class ItemActivity extends AppCompatActivity {
    ImageView imageView,btnPlus,btnMin,btnBack;
    TextView nameView,priceView,descView,btnAdd,itemCount;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        imageView=findViewById(R.id.itemImg);
        nameView=findViewById(R.id.name);
        priceView=findViewById(R.id.price);
        descView=findViewById(R.id.desc);
        btnMin=findViewById(R.id.btnMin);
        itemCount=findViewById(R.id.itemCount);
        btnPlus=findViewById(R.id.btnPlus);
        btnAdd=findViewById(R.id.btnAdd);
        btnBack=findViewById(R.id.btnBack);

        btnBack.setOnClickListener(view -> {
            startActivity(new Intent(this,HomeActivity.class));
        });

        count=1;
        Item item= (Item) getIntent().getSerializableExtra("item");
        nameView.setText(item.getName());
        priceView.setText("$"+item.getPrice());
        Glide.with(getApplicationContext()).load(item.getImage()).into(imageView);
        itemCount.setText(String.valueOf(count));

        btnMin.setOnClickListener(view -> {
            if(count>1){
                count-=1;
            }
            itemCount.setText(String.valueOf(count));
        });
        btnPlus.setOnClickListener(view -> {
            count+=1;
            itemCount.setText(String.valueOf(count));
        });

        btnAdd.setOnClickListener(view -> {
            AppDb db=AppDb.getInstance(this.getApplicationContext());
            CartItem cartItem=new CartItem();
            cartItem.name=item.getName();
            cartItem.price=item.getPrice();
            cartItem.count=count;
            cartItem.image=item.getImage();

            try {
                db.cartItemDao().addCartItem(cartItem);
                Toast.makeText(this, "Added to Cart Successfully!", Toast.LENGTH_SHORT).show();
            }catch (SQLiteConstraintException e){
                Toast.makeText(this, "Exist in cart already!", Toast.LENGTH_SHORT).show();
            }
            finish();
        });

    }
}