package com.example.taskque_mobile_app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent) {
        // Get id & message from intent.
        int notificationId = intent.getIntExtra("notificationId", 0);
        String message = intent.getStringExtra("todo");

        // When notification is tapped, call MainActivity.
        Intent mainIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);


        // building the notification

        Notification notification = new NotificationCompat.Builder(context, "not")
                // Show controls on lock screen even when user hides sensitive content.
                .setSmallIcon(R.drawable.notification_icon)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                // Apply the media style template
                .setContentTitle("It's Time") //title of the notification
                .setContentText(message) //notification context
                .setOnlyAlertOnce(true)
                .setContentIntent(contentIntent)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0,notification);
    }
}
