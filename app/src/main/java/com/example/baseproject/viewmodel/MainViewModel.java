package com.example.baseproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.baseproject.db.model.Grocery;
import com.example.baseproject.db.repository.GroceryRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<Grocery>> mGroceries;
    private GroceryRepository mRepo;


    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepo = new GroceryRepository(application);
        mGroceries = mRepo.getLiveDataAllGroceries();
    }

    public LiveData<List<Grocery>> getGroceries(){
        return mGroceries;
    }

    public void insert(Grocery grocery){
        mRepo.insert(grocery);
    }

    public void update(Grocery grocery){
        mRepo.update(grocery);
    }

    public void delete(Grocery grocery){
        mRepo.delete(grocery);
    }

    public void deleteAll(){
        mRepo.deleteAllGroceries();
    }
}
