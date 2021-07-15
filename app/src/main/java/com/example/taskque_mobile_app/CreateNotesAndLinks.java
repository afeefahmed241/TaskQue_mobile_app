package com.example.taskque_mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class CreateNotesAndLinks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notes_and_links);


        RecyclerView notelink=findViewById(R.id.linknote_recycler);

        NotesAndLinks[] notesAndLinks={
                new NotesAndLinks("Etai note ","Etai link"),
                new NotesAndLinks("Etai note ","Etai link"),
                new NotesAndLinks("Etai note ","Etai link"),
                new NotesAndLinks("Etai note ","Etai link")

        };

        NoteAdapter noteAdapter=new NoteAdapter(notesAndLinks);
        notelink.setAdapter(noteAdapter);

    }
}