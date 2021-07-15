package com.example.taskque_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class EditTask extends AppCompatActivity {

    EditText etTitle,etDes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        Bundle bundle = getIntent().getExtras();

        int TaskID = bundle.getInt("TaskID");

        etTitle = findViewById(R.id.textview_tasktitle);
        etDes = findViewById(R.id.textview_taskDescription);

        TasksDB db = new TasksDB(this);
        db.open();
        Tasks t= db.getTasksData(TaskID+"");
        db.close();

        etTitle.setText(t.getTitle());
        etDes.setText(t.getDescription());




    }

}