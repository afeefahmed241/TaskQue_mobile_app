package com.example.taskque_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class NewTask extends AppCompatActivity {

    EditText etTitle,etDes;
    Button btnSave,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        etTitle = findViewById(R.id.titledoes);
        etDes = findViewById(R.id.etdescription);

        btnSave = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void btnSaveData(View v)
    {
        String title = etTitle.getText().toString().trim();
        String des = etDes.getText().toString().trim();

        TasksDB db = new TasksDB(NewTask.this);
        db.open();
        db.entryTasks(title,des);
       // db.close();
       // db.open();
        int tID = db.getLatestTaskID();
      //  db.close();
      //  db.open();
        db.entryTimers(tID,2021,6,15,20,50,"One Time");
      //  db.close();
     //   db.open();
        db.entryTodayTimers(tID,2021,6,15,20,50,"One Time");
        ArrayList<Timers> t =  db.getTimersData();

        db.close();

        ApplicationClass.pendingList.clear();
        for(int i=0;i<t.size();i++)
        {
            ApplicationClass.pendingList.add(t.get(i));
        }

        Toast.makeText(NewTask.this, "Saved", Toast.LENGTH_SHORT).show();

        finish();
    }

}