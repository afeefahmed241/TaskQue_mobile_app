package com.example.taskque_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class NewTask extends AppCompatActivity {

    EditText etTitle,etDes,etTime;
    Button btnSave,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        etTitle = findViewById(R.id.titledoes);
        etDes = findViewById(R.id.etdescription);
        etTime = findViewById(R.id.ettimeline);

        btnSave = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String des = etDes.getText().toString().trim();
                int min = Integer.parseInt(etTime.getText().toString().trim());

                Calendar time = Calendar.getInstance();
                time.set(Calendar.YEAR,2021);
                time.set(Calendar.MONTH,06);
                time.set(Calendar.DAY_OF_MONTH,28);
                time.set(Calendar.HOUR_OF_DAY,1);
                time.set(Calendar.MINUTE,min);
                time.set(Calendar.SECOND,0);
                long alarmTime = time.getTimeInMillis();



                TasksDB db = new TasksDB(NewTask.this);
                db.open();
                db.entryTasks(title,des);
                // db.close();
                // db.open();
                int tID = db.getLatestTaskID();
                //  db.close();
                //  db.open();
                db.entryTimers(tID,2021,6,28,1,min,"One Time");
                //  db.close();
                //   db.open();
                //db.entryTodayTimers(tID,2021,6,15,20,50,"One Time");
                int timerID = db.getLatestTimerID();
                ArrayList<Timers> t =  db.getTimersData();
                int requestCode  = db.getRequestCode();
                db.updateRequestCode(requestCode+1);

                db.close();


                Intent intent = new Intent(NewTask.this, AlarmReceiver.class);
                intent.putExtra("taskId", tID);
                intent.putExtra("timerId", timerID);

                // getBroadcast(context, requestCode, intent, flags)
                PendingIntent alarmIntent = PendingIntent.getBroadcast(NewTask.this, requestCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP,alarmTime,alarmIntent);

                Toast.makeText(NewTask.this, "Saved", Toast.LENGTH_SHORT).show();

                finish();
            }
        });


    }



}