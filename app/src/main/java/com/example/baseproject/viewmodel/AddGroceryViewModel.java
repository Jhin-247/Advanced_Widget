package com.example.baseproject.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.baseproject.db.callback.DBCallback;
import com.example.baseproject.db.model.Grocery;
import com.example.baseproject.resipotory.GroceryRepository;

public class AddGroceryViewModel extends AndroidViewModel {
    private GroceryRepository mRepo;

    public AddGroceryViewModel(@NonNull Application application) {
        super(application);
        mRepo = new GroceryRepository(application);
    }

    public void insert(Grocery grocery, DBCallback callback) {
        if(!TextUtils.isEmpty(grocery.getName()) && !TextUtils.isEmpty(grocery.getNumber())){
            mRepo.insert(grocery);
            callback.onSaveSuccess();
        } else {
            callback.onSaveFailed();
        }
    }
}
