package com.example.mvvm.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mvvm.R;

import java.util.List;

import localDataSrc.Notes;
import retrofit2.Retrofit;
import viewModels.ViewModelActivity;

public class NotesController extends AppCompatActivity {

    EditText editText;
    Button addNoteBtn;
    Button deleteAllBtn;
    TextView textViewNotes;
    ViewModelActivity viewModelActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextTextPersonName);
        addNoteBtn = findViewById(R.id.button);
        deleteAllBtn = findViewById(R.id.button2);
        textViewNotes = findViewById(R.id.textView);
        viewModelActivity = new ViewModelProvider(this).get(ViewModelActivity.class); // getting instance of VM




        //observing live list data for notes
        viewModelActivity.getNotes().observe(this, new Observer<List<localDataSrc.Notes>>() {
            @Override
            public void onChanged(List<localDataSrc.Notes> notes) {
                textViewNotes.setText("");
                for(localDataSrc.Notes note : notes)
                {
                    textViewNotes.append(note.getNote() + "\n");
                }
            }
        });
    }




        @Override
        protected void onPause () {
            super.onPause();
            SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", textViewNotes.getText().toString());
            editor.apply();
        }

        @Override
        protected void onResume () {
            super.onResume();
            SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
            String name = preferences.getString("name", "default name");
            textViewNotes.setText(name);
        }

    public void addNote(View view)
    {
        viewModelActivity.addNote(editText.getText().toString());
    }

    public void deleteAll(View view)
    {
        viewModelActivity.deleteAllNotes();
    }







}