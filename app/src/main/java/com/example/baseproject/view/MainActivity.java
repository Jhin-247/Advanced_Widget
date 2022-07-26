package com.example.baseproject.view;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.baseproject.R;
import com.example.baseproject.adapter.CallBack;
import com.example.baseproject.adapter.GroceryAdapter;
import com.example.baseproject.databinding.ActivityMainBinding;
import com.example.baseproject.db.model.Grocery;
import com.example.baseproject.viewmodel.MainViewModel;
import com.example.baseproject.widget.WidgetProvider;

public class MainActivity extends AppCompatActivity implements CallBack {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainViewModel mViewModel;
    private GroceryAdapter adapter;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initVariables();
        setupRecyclerView();
        setUpOnClick();
    }

    private void setUpOnClick() {
        mBinding.btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddGroceryActivity.class);
            startActivity(intent);
        });
    }

    private void setupRecyclerView() {
        mBinding.rcvGroceries.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rcvGroceries.setAdapter(adapter);
        mViewModel.getGroceries().observe(this, groceries -> {
            adapter.setGroceries(groceries);
        });
    }

    private void initVariables() {
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        adapter = new GroceryAdapter(this);
    }

    @Override
    public void onItemClick(Grocery grocery) {
        mViewModel.delete(grocery);
        updateWidget();
    }

    void updateWidget(){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        ComponentName componentName = new ComponentName(getPackageName(), WidgetProvider.class.getName());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(componentName);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.lv_items);
    }
}