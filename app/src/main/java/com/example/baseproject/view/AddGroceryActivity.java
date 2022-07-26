package com.example.baseproject.view;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ActivityAddGroceryBinding;
import com.example.baseproject.db.callback.DBCallback;
import com.example.baseproject.db.model.Grocery;
import com.example.baseproject.viewmodel.AddGroceryViewModel;
import com.example.baseproject.widget.WidgetProvider;

public class AddGroceryActivity extends AppCompatActivity implements DBCallback {
    private ActivityAddGroceryBinding mBinding;
    private Grocery grocery;
    private AddGroceryViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_grocery);

        initVariables();
        setUpOnClick();

    }

    private void setUpOnClick() {
        mBinding.btnIncrease.setOnClickListener(v -> {
            int number = Integer.parseInt(grocery.getNumber()) + 1;
            mBinding.edNumber.setText(String.valueOf(number));
        });

        mBinding.btnDecrease.setOnClickListener(v -> {
            int number = Integer.parseInt(grocery.getNumber()) - 1;
            mBinding.edNumber.setText(String.valueOf(number));
        });

        mBinding.btnAdd.setOnClickListener(v -> mViewModel.insert(grocery, this));

        mBinding.btnCancel.setOnClickListener(v -> finish());
    }

    private void initVariables() {
        grocery = new Grocery();
        grocery.setNumber("0");
        mBinding.setGrocery(grocery);

        mViewModel = new ViewModelProvider(this).get(AddGroceryViewModel.class);
    }

    @Override
    public void onSaveSuccess() {
        updateWidget();
        finish();
    }

    @Override
    public void onSaveFailed() {
        Toast.makeText(this, "???", Toast.LENGTH_SHORT).show();
    }

    void updateWidget() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        ComponentName componentName = new ComponentName(getPackageName(), WidgetProvider.class.getName());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(componentName);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.lv_items);
    }
}
