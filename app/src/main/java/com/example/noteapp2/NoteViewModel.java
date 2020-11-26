package com.example.noteapp2;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    NoteDao noteDao;
    LiveData<List<Note>> allNotes;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    void insert(Note note){
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    void update(Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    void deleteAllNotes(){
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    LiveData<List<Note>> getAllNotes(){
        return noteDao.getAllNotes();
    }

    class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        NoteDao noteDao;
        public InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        NoteDao noteDao;
        public UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        NoteDao noteDao;
        public DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>{
        NoteDao noteDao;
        public DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
