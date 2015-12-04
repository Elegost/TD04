package el3gost.td04;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RemoteViews;

import java.util.Random;

public class SimpleWidgetProvider extends AppWidgetProvider {

    public static String ACTION_WIDGET_CONFIGURE = "ConfigureWidget";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this
        // provider
        for (int i = 0; i < N; i++) {
            int widgetId = appWidgetIds[i];
            String number = String.format("%03d", (new Random().nextInt(900) + 100));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            remoteViews.setTextViewText(R.id.textView, number);

            Intent intent = new Intent(context, SimpleWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.actionButton, pendingIntent);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);

        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        // Prepare widget views
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}
