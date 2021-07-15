package com.example.taskque_mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        Button pendingBtn,overdueBtn;
        pendingBtn=findViewById(R.id.pending_button);
        overdueBtn=findViewById(R.id.overdue_button);

        setFragment(new PedingFragment());


        pendingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                replaceFragment(new PedingFragment());
                    
                    
                

            }
        });

        overdueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(new OverDueFragment());




            }
        });




        TextView dateView=findViewById(R.id.textview_date);
        //TextView timeview=findViewById(R.id.textview_time);





        FloatingActionButton createButton=findViewById(R.id.activity_main_floatingbutton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "yemete kudasai", Toast.LENGTH_LONG).show();
            }
        });


        //RecyclerView homeRecyclerView=findViewById(R.id.home_recycler_view);



       /* Task[] taskDetails={
                new Task("Class","OS class","11.00AM"),
                new Task("Class","MPAL class","9.00AM"),
                new Task("Class","SIM class","1.00pM"),
                new Task("Class","MATH class","2.00pM"),
                new Task("Class","NET class","10.00AM")
        };*/

       // TaskAdapter taskAdapter=new TaskAdapter(taskDetails);
       // homeRecyclerView.setAdapter(taskAdapter);

        //showDateTime(dateView);
        Calendar calender=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("EEEE, dd-MM-yyyy");
       // SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("hh:mm a");

        String date=simpleDateFormat1.format(calender.getTime());
       // String time=simpleDateFormat2.format(calender.getTime());



        dateView.setText(date);
       // timeview.setText(time);






    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
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


}