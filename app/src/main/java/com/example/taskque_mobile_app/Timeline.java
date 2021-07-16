package com.example.taskque_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class Timeline extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    Button datePickerBtn,timePickerBtn;
    int hour,minute;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);


        //dropdown menu starts

        Spinner spinner;
        ArrayAdapter<CharSequence> adapter;

        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();

                /*replaceFragment(new OneTime()){


                }*/

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //drop down menu ends

        //datePicker and timepicker starts

        initDatePicker();
        datePickerBtn=findViewById(R.id.datePickerButton_id);
        datePickerBtn.setText(getTodaysDate());

        timePickerBtn=findViewById(R.id.timePickerButton_id);


    }

    private String getTodaysDate() {

        Calendar cal=Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month=month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private void initDatePicker() {


        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month=month+1;
                String date=makeDateString(dayOfMonth,month,year);
                datePickerBtn.setText(date);

            }
        };

        Calendar cal=Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);

    }

    private String makeDateString(int dayOfMonth, int month, int year) {
        return getMonthFormat(month)+" "+dayOfMonth+" "+year;
    }

    private String getMonthFormat(int month) {

        if(month==1)
            return "JAN";
        if(month==2)
            return "FEB";
        if(month==3)
            return "MAR";
        if(month==4)
            return "APR";
        if(month==5)
            return "MAY";
        if(month==6)
            return "JUN";
        if(month==7)
            return "JUL";
        if(month==8)
            return "AUG";
        if(month==9)
            return "SEP";
        if(month==10)
            return "OCT";
        if(month==11)
            return "NOV";
        if(month==12)
            return "DEC";

        return "JUL";


    }

    public void openDatePicker(View view) {

        datePickerDialog.show();
    }

    public void popTimePicker(View view) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour=selectedHour;
                minute=selectedMinute;
                timePickerBtn.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));

            }
        };

        int style2=AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog=new TimePickerDialog(this,style2,onTimeSetListener,hour,minute,false);
        timePickerDialog.setTitle("Set Time");
        timePickerDialog.show();
    }


    //date picker ends




}