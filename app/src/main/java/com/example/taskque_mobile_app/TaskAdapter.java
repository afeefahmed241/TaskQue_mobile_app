package com.example.taskque_mobile_app;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViweHolder> {

    private Task[] tasks;

    public TaskAdapter(Task[] tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dailytask,parent,false);

        return new TaskViweHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViweHolder holder, int position) {

        holder.bind(tasks[position]);

    }

    @Override
    public int getItemCount() {
        return tasks.length;
    }

    static class TaskViweHolder extends RecyclerView.ViewHolder {

        TextView taskTitle,taskDescription,taskTime;


        @SuppressLint("CutPasteId")
        public TaskViweHolder(@NonNull View itemView) {
            super(itemView);
            taskTitle=itemView.findViewById(R.id.tasktime_cardview);
            taskDescription=itemView.findViewById(R.id.taskdescription_cardview);
            taskTime=itemView.findViewById(R.id.tasktime_cardview);
        }

        public void bind(Task task){

            taskTitle.setText(task.title);
            taskDescription.setText(task.description);
            taskTime.setText(task.time);

        }


    }




}

