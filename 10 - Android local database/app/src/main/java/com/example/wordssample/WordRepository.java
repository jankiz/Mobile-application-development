package com.example.wordssample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDAO dao;
    private LiveData<List<Word>> words;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getInstance(application);
        dao = db.wordDAO();
        words = dao.getWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return words;
    }

    public void insert(Word word) {
        new Insert(this.dao).execute(word);
    }


    private static class Insert extends AsyncTask<Word, Void, Void> {
        private WordDAO mDAO;

        Insert(WordDAO dao) {
            this.mDAO = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mDAO.insert(words[0]);
            return null;
        }
    }
}
