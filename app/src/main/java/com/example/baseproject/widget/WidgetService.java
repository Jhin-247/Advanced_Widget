package com.example.baseproject.widget;

import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.baseproject.R;
import com.example.baseproject.db.model.Grocery;
import com.example.baseproject.resipotory.GroceryRepository;

import java.util.ArrayList;
import java.util.List;

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyWidgetItemFactory(getApplicationContext(), intent);
    }

    public static class MyWidgetItemFactory implements RemoteViewsFactory {
        private final Context context;
        private final int appWidgetId;
        private final GroceryRepository mRepo;
        private List<Grocery> groceries;

        MyWidgetItemFactory(Context context, Intent intent) {
            this.context = context;
            this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            mRepo = new GroceryRepository((Application) context.getApplicationContext());
        }

        @Override
        public void onCreate() {
            groceries = new ArrayList<>();
        }

        @Override
        public void onDataSetChanged() {
            groceries = mRepo.getListGroceries();
        }

        @Override
        public void onDestroy() {
            groceries = null;
        }

        @Override
        public int getCount() {
            return groceries.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_widget_item);
            remoteViews.setTextViewText(R.id.tv_name, groceries.get(position).getName());
            remoteViews.setTextViewText(R.id.tv_num, groceries.get(position).getNumber());

            Bundle extras = new Bundle();
            extras.putInt(WidgetProvider.EXTRA_ITEM, position);
            Intent fillInIntent = new Intent();
            fillInIntent.putExtras(extras);
            // Make it possible to distinguish the individual on-click
            // action of a given item
            remoteViews.setOnClickFillInIntent(R.id.btn_delete, fillInIntent);

            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
