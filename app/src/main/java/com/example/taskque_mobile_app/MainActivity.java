package com.example.taskque_mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TaskAdapter.ItemClicked{


    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pendingBtn,overdueBtn;
        pendingBtn=findViewById(R.id.pending_button);
        overdueBtn=findViewById(R.id.overdue_button);

        //setFragment(new PendingFragment());



        TasksDB db = new TasksDB(this);
        db.open();
        ArrayList<Timers> t =  db.getTimersData();


        db.close();

        for(int i=0;i<t.size();i++)
        {
            ApplicationClass.pendingList.add(t.get(i));
        }
        recyclerView = findViewById(R.id.rc_home);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new TaskAdapter(MainActivity.this,ApplicationClass.pendingList);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();




        pendingBtn.setOnClickListener(new View.OnClickListener() {   //home page fragment
            @Override
            public void onClick(View v) {




                //replaceFragment(new PendingFragment());
                    
                    
                

            }
        });

        overdueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent overDue= new Intent(getApplicationContext(), OverDueTaskActivity.class);
                startActivity(overDue);
               // replaceFragment(new OverDueFragment());




            }
        });




        TextView dateView=findViewById(R.id.textview_date);
        //TextView timeview=findViewById(R.id.textview_time);





        FloatingActionButton createButton=findViewById(R.id.activity_main_floatingbutton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(MainActivity.this, "yemete kudasai", Toast.LENGTH_LONG).show();*/
                Intent buttonNewTask= new Intent(getApplicationContext(), NewTask.class);
                startActivity(buttonNewTask);

            }
        });



        //showDateTime(dateView);
        Calendar calender=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("EEEE, dd-MM-yyyy");
       // SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("hh:mm a");

        String date=simpleDateFormat1.format(calender.getTime());
       // String time=simpleDateFormat2.format(calender.getTime());



        dateView.setText(date);
       // timeview.setText(time);






    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        TasksDB db = new TasksDB(this);
        db.open();
        ArrayList<Timers>t = db.getTimersData();
        db.close();
        ApplicationClass.pendingList.clear();
        for(int i=0;i<t.size();i++)
        {
            ApplicationClass.pendingList.add(t.get(i));
        }
        myAdapter.notifyDataSetChanged();
    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();  //to set the default fragment
        fragmentManager.beginTransaction()
                .replace(R.id.recyclerview_holder, fragment)
                .commit();
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.recyclerview_holder,fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onItemClicked(int index) {
        //call the Edit Task activity
        Intent editTask= new Intent(getApplicationContext(), EditTask.class);
        editTask.putExtra("TaskID",ApplicationClass.pendingList.get(index).getTaskID());
        editTask.putExtra("TimersID",ApplicationClass.pendingList.get(index).getTimersID());
        startActivity(editTask);

    }
}