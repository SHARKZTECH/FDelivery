package com.example.fdelivery.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CartItemDao {
    @Insert
    void addCartItem(CartItem... cartItem);

    @Query("SELECT * FROM CartItem")
    List<CartItem> getAllCartItems();

    @Update
    void updateCartItem(CartItem cartItem);
}
