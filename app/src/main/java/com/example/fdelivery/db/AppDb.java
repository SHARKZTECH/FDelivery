package com.example.fdelivery.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = CartItem.class,version = 1)
public abstract class AppDb extends RoomDatabase {
    public abstract CartItemDao cartItemDao();

    private static AppDb INSTANCE;

    public static AppDb getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDb.class,"CartItem")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
