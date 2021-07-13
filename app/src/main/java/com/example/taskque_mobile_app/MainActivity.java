package com.example.taskque_mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView dateView=findViewById(R.id.textview_date);
        TextView timeview=findViewById(R.id.textview_time);





        FloatingActionButton createButton=findViewById(R.id.activity_main_floatingbutton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "yemete kudasai", Toast.LENGTH_LONG).show();
            }
        });


        RecyclerView homeRecyclerView=findViewById(R.id.home_recycler_view);



        Task[] taskDetails={
                new Task("Class","OS class","11.00AM"),
                new Task("Class","MPAL class","9.00AM"),
                new Task("Class","SIM class","1.00pM"),
                new Task("Class","MATH class","2.00pM"),
                new Task("Class","NET class","10.00AM")
        };

        TaskAdapter taskAdapter=new TaskAdapter(taskDetails);
        homeRecyclerView.setAdapter(taskAdapter);

        //showDateTime(dateView);
        Calendar calender=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("EEEE, dd-MM-yyyy");
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("hh:mm a");

        String date=simpleDateFormat1.format(calender.getTime());
        String time=simpleDateFormat2.format(calender.getTime());



        dateView.setText(date);
        timeview.setText(time);






    }


}