package com.example.baseproject.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.db.model.Grocery;
import com.example.baseproject.databinding.ItemGroceryBinding;

import java.util.List;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryHolder> {
    private List<Grocery> groceries;

    @NonNull
    @Override
    public GroceryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroceryHolder(ItemGroceryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryHolder holder, int position) {
        Grocery grocery = groceries.get(position);
        holder.mBinding.setGrocery(grocery);
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return groceries == null ? 0 : groceries.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setGroceries(List<Grocery> groceries) {
        this.groceries = groceries;
        notifyDataSetChanged();
    }

    public static class GroceryHolder extends RecyclerView.ViewHolder {
        ItemGroceryBinding mBinding;

        public GroceryHolder(@NonNull ItemGroceryBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}
