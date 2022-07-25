package com.example.baseproject.widget;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.baseproject.view.MainActivity;
import com.example.baseproject.R;

public class ExampleWidgetProvider extends AppWidgetProvider {

    /*
     * Hàm chịu trách nhiệm cập nhật widget
     * setonclick cho hàm này
     * appWidgetIds: là id của các instance widget của mình (có thể thêm nhiều hơn là 1 instance của widget tại home screen)
     *
     *
     */

    @SuppressLint("UnspecifiedImmutableFlag")
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
            } else {
                pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            }
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
            views.setOnClickPendingIntent(R.id.btn_exam, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
