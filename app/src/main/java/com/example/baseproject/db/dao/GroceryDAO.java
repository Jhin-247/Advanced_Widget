package com.example.baseproject.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.baseproject.db.model.Grocery;

import java.util.List;

@Dao
public interface GroceryDAO {
    @Insert
    void insert(Grocery grocery);

    @Update
    void update(Grocery grocery);

    @Delete
    void delete(Grocery grocery);

    @Query("SELECT * FROM grocery")
    LiveData<List<Grocery>> getAllGroceries();

    @Query("DELETE FROM grocery")
    void deleteAllGroceries();

}
