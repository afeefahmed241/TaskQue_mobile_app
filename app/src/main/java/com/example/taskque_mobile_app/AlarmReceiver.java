package com.example.taskque_mobile_app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Get id & message from intent.
        int taskId = intent.getIntExtra("taskId", 0);
        int timerId = intent.getIntExtra("timerId", 0);

        TasksDB db = new TasksDB(context);
        db.open();
        Tasks t = db.getTasksData(taskId + "");
        Timers timers = db.getATimersData(timerId + "");


        // When notification is tapped, call MainActivity.
        Intent mainIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);


        // building the notification

        Notification notification = new NotificationCompat.Builder(context, "not")
                // Show controls on lock screen even when user hides sensitive content.
                .setSmallIcon(R.drawable.notification_icon)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                // Apply the media style template
                .setContentTitle(t.getTitle()) //title of the notification
                .setContentText(t.getDescription()) //notification context
                .setOnlyAlertOnce(true)
                .setContentIntent(contentIntent)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0, notification);


        db.deleteTimersEntry(timerId + "");
        db.entryTodayTimers(timers.getTaskID(), timers.getYear(), timers.getMonth(), timers.getDayOFMonth(), timers.getHourOFDay(), timers.getMinute(), timers.getType());
        db.close();
    }
}
