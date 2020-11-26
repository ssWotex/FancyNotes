package com.example.noteapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddEditNoteActivity extends AppCompatActivity {

    static String EXTRA_TITLE = "title";
    static String EXTRA_DESCRIPTION = "description";
    static String EXTRA_ID = "id";
    static String EXTRA_PRIORITY = "priority";

    EditText et_title;
    EditText et_description;
    NumberPicker number_picker_priority;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        et_title = findViewById(R.id.et_title);
        et_description = findViewById(R.id.et_description);
        number_picker_priority = findViewById(R.id.number_picker_priority);
        number_picker_priority.setMaxValue(10);
        number_picker_priority.setMinValue(1);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");

        if(getIntent().hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            et_description.setText(getIntent().getStringExtra(EXTRA_DESCRIPTION));
            et_title.setText(getIntent().getStringExtra(EXTRA_TITLE));
            number_picker_priority.setValue(getIntent().getIntExtra(EXTRA_PRIORITY, 1));
        }
        else{
            setTitle("Add Note");
        }
    }

    public void saveNote(){
        String title = et_title.getText().toString();
        String description = et_description.getText().toString();
        int priority = number_picker_priority.getValue();
        if(!title.equals("") && !description.equals("")){
            Intent intent = new Intent();
            intent.putExtra(EXTRA_PRIORITY, priority);
            intent.putExtra(EXTRA_DESCRIPTION, description);
            intent.putExtra(EXTRA_TITLE, title);
            if(getIntent().hasExtra(EXTRA_ID)){
                intent.putExtra(EXTRA_ID, getIntent().getIntExtra(EXTRA_ID, 0));
            }
            setResult(RESULT_OK, intent);
            finish();
        }
        else{
            Toast.makeText(this, "Please input all needed infos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addditnotemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}