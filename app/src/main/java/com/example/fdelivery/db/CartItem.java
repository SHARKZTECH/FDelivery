package com.example.fdelivery.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(indices ={@Index(value ="name",unique = true)})
public class CartItem implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "price")
    public double price;
    @ColumnInfo(name = "image")
    public int image;
    @ColumnInfo(name = "count")
    public int count;

}
