package com.example.fdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView btn=findViewById(R.id.btnStart);

        btn.setOnClickListener(view -> {
            Intent i=new Intent(this,HomeActivity.class);
            startActivity(i);
        });
    }
}