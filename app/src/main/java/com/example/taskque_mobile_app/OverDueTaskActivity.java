package com.example.taskque_mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class OverDueTaskActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_due_task);

        TasksDB db = new TasksDB(this);
        db.open();
        ArrayList<Timers> overDueList = db.getTodayTimersData();

        db.close();

        recyclerView = findViewById(R.id.rec_overdue);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(OverDueTaskActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new TaskAdapter(OverDueTaskActivity.this,overDueList);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
}