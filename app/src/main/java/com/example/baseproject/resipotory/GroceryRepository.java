package com.example.baseproject.resipotory;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.lifecycle.LiveData;

import com.example.baseproject.db.GroceryDatabase;
import com.example.baseproject.db.dao.GroceryDAO;
import com.example.baseproject.db.model.Grocery;

import java.util.List;

public class GroceryRepository{

    private GroceryDAO groceryDAO;
    private LiveData<List<Grocery>> mGroceries;

    public GroceryRepository(Application application){
        GroceryDatabase groceryDatabase = GroceryDatabase.getInstance(application);
        groceryDAO = groceryDatabase.mGroceryDAO();
        mGroceries = groceryDAO.getAllLiveDataGroceries();
    }

    public void insert(Grocery grocery){
        HandlerThread handlerThread = new HandlerThread("insert");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                groceryDAO.insert(grocery);
            }
        });
    }

    public void delete(Grocery grocery){
        HandlerThread handlerThread = new HandlerThread("delete");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                groceryDAO.delete(grocery);
            }
        });
    }

    public List<Grocery> getListGroceries(){
        return groceryDAO.getAllGrocery();
    }



}













