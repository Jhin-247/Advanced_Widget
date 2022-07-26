package com.example.baseproject.widget;

import android.app.Application;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.RemoteViews;

import com.example.baseproject.R;
import com.example.baseproject.db.model.Grocery;
import com.example.baseproject.resipotory.GroceryRepository;
import com.example.baseproject.view.MainActivity;

public class WidgetProvider extends AppWidgetProvider {
    public static final String ACTION = "com.example.baseproject.widget.DELETE";
    public static final String EXTRA_ITEM = "com.example.android.stackwidget.EXTRA_ITEM";
    /*
     * Hàm chịu trách nhiệm cập nhật widget
     * setonclick cho hàm này
     * appWidgetIds: là id của các instance widget của mình (có thể thêm nhiều hơn là 1 instance của widget tại home screen)
     */

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {

            Intent serviceIntent = new Intent(context, WidgetService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_widget);
            remoteViews.setRemoteAdapter(R.id.lv_items, serviceIntent);

            Intent actionDoneIntent = new Intent(context, WidgetProvider.class);
            actionDoneIntent.setAction(ACTION);
            actionDoneIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            actionDoneIntent.setData(Uri.parse(actionDoneIntent.toUri(Intent.URI_INTENT_SCHEME)));
            PendingIntent actionDonePendingIntent = PendingIntent.getBroadcast(context, 0, actionDoneIntent, 0 | PendingIntent.FLAG_MUTABLE);
            remoteViews.setPendingIntentTemplate(R.id.lv_items, actionDonePendingIntent);

            Intent openAppIntent = new Intent(context, MainActivity.class);
            PendingIntent pendingMainIntent = PendingIntent.getActivity(context.getApplicationContext(), 1, openAppIntent, 0 | PendingIntent.FLAG_MUTABLE);
            remoteViews.setOnClickPendingIntent(R.id.btn_open, pendingMainIntent);

            remoteViews.setEmptyView(R.id.lv_items, R.id.empty_view);
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
            int position = intent.getIntExtra(EXTRA_ITEM, 0);
            HandlerThread handlerThread = new HandlerThread("query");
            handlerThread.start();
            Handler handler = new Handler(handlerThread.getLooper());
            handler.post(() -> {
                GroceryRepository groceryRepository = new GroceryRepository((Application) context.getApplicationContext());
                Grocery grocery = groceryRepository.getListGroceries().get(position);
                groceryRepository.delete(grocery);
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context.getApplicationContext());
                ComponentName componentName = new ComponentName(context.getApplicationContext().getPackageName(), WidgetProvider.class.getName());
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(componentName);
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.lv_items);
            });
        }
        super.onReceive(context, intent);
    }
}
