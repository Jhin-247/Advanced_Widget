package com.example.baseproject.db;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.baseproject.db.dao.GroceryDAO;
import com.example.baseproject.db.model.Grocery;

@Database(entities = {Grocery.class}, version = 1)
public abstract class GroceryDatabase extends RoomDatabase {
    private static GroceryDatabase sInstance;

    public abstract GroceryDAO mGroceryDAO();

    public static synchronized GroceryDatabase getInstance(Context context){
        if(sInstance == null){
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                    GroceryDatabase.class,"grocery_database")
                    .fallbackToDestructiveMigration().build();
        }
        return sInstance;
    }

}
