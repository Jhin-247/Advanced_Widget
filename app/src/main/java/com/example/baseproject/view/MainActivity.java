package com.example.baseproject.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ActivityMainBinding;
import com.example.baseproject.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mBinding;
    private MainViewModel mViewModel;
    private GroceryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        adapter = new GroceryAdapter();
        mBinding.rcvGroceries.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rcvGroceries.setAdapter(adapter);
        mViewModel.getGroceries().observe(this, groceries -> adapter.setGroceries(groceries));

        mBinding.btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddGroceryActivity.class);
            startActivity(intent);
        });
    }
}