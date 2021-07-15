package com.example.taskque_mobile_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private NotesAndLinks[] notesAndLinks;

    public NoteAdapter(NotesAndLinks[] notesAndLinks) {
        this.notesAndLinks = notesAndLinks;
    }

    @Override
    public int getItemCount() {
        return notesAndLinks.length;
    }




    @NonNull

    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notesaandlinks,parent,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder holder, int position) {
        holder.bind(notesAndLinks[position]);



    }




    static class NoteViewHolder extends RecyclerView.ViewHolder{


        TextView notes,links;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            notes=itemView.findViewById(R.id.tasknotesTitle_cardview);
            links=itemView.findViewById(R.id.taskdescription_cardview);
        }

        public void bind(NotesAndLinks notesAndLinks){

            notes.setText(notesAndLinks.notes);
            links.setText(notesAndLinks.links);

        }
    }
}
