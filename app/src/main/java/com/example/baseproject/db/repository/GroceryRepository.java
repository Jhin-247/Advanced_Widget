package com.example.baseproject.db.repository;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.lifecycle.LiveData;

import com.example.baseproject.db.GroceryDatabase;
import com.example.baseproject.db.dao.GroceryDAO;
import com.example.baseproject.db.model.Grocery;

import java.util.List;

public class GroceryRepository {
    private GroceryDAO groceryDAO;
    private LiveData<List<Grocery>> groceries;

    public GroceryRepository(Application application){
        GroceryDatabase groceryDatabase = GroceryDatabase.getInstance(application);
        groceryDAO = groceryDatabase.mGroceryDAO();
        groceries = groceryDAO.getAllGroceries();
    }

    public void insert(Grocery grocery){
        HandlerThread thread = new HandlerThread("insert");
        thread.start();
        Handler handle = new Handler(thread.getLooper());
        handle.post(() -> groceryDAO.insert(grocery));
    }

    public void update(Grocery grocery){
        HandlerThread thread = new HandlerThread("update");
        thread.start();
        Handler handle = new Handler(thread.getLooper());
        handle.post(() -> groceryDAO.update(grocery));
    }

    public void delete(Grocery grocery){
        HandlerThread thread = new HandlerThread("delete");
        thread.start();
        Handler handle = new Handler(thread.getLooper());
        handle.post(() -> groceryDAO.delete(grocery));
    }

    public void deleteAllGroceries(){
        HandlerThread thread = new HandlerThread("deleteAllGroceries");
        thread.start();
        Handler handle = new Handler(thread.getLooper());
        handle.post(groceryDAO::deleteAllGroceries);
    }

    public LiveData<List<Grocery>> getAllGroceries(){
        return groceries;
    }
}
