package com.example.taskque_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditTask extends AppCompatActivity {

    EditText etTitle,etDes;
    TextView btnNotes,btnLinks;
    int TaskID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        Bundle bundle = getIntent().getExtras();

         TaskID = bundle.getInt("TaskID");

        etTitle = findViewById(R.id.textview_tasktitle);
        etDes = findViewById(R.id.textview_taskDescription);
        btnNotes = findViewById(R.id.textview_tasknotes);
        btnLinks = findViewById(R.id.textview_tasklinks);

        TasksDB db = new TasksDB(this);
        db.open();
        Tasks t= db.getTasksData(TaskID+"");
        db.close();

        etTitle.setText(t.getTitle());
        etDes.setText(t.getDescription());

        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notes= new Intent(getApplicationContext(), CreateNotes.class);
                notes.putExtra("TaskID",TaskID);
                startActivity(notes);
            }
        });

        btnLinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent links= new Intent(getApplicationContext(), createLinks.class);
                links.putExtra("TaskID",TaskID);
                startActivity(links);
            }
        });




    }

}