package com.example.baseproject.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ActivityAddGroceryBinding;
import com.example.baseproject.db.model.Grocery;

public class AddGroceryActivity extends AppCompatActivity {
    private ActivityAddGroceryBinding mBinding;
    private Grocery mGrocery;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_grocery);
        mGrocery = new Grocery();
        mBinding.setGrocery(mGrocery);
        mBinding.executePendingBindings();
    }
}
