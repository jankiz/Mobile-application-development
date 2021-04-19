package com.example.wordssample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDAO wordDAO();

    private static WordRoomDatabase instance;

    public static WordRoomDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (WordRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(), WordRoomDatabase.class,
                            "word_database").fallbackToDestructiveMigration()
                            .addCallback(populationCallback).build();
                }
            }
        }

        return instance;
    }

    private static RoomDatabase.Callback populationCallback = new RoomDatabase.Callback() {
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            new InitDb(instance).execute();
        }
    };

    private static class InitDb extends AsyncTask<Void, Void, Void> {
        private WordDAO dao;
        String[] words = { "da", "nin", "ja" };

        InitDb(WordRoomDatabase db) {
            this.dao = db.wordDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.dao.deleteAll();

            for (int i = 0; i < words.length; i++)
                this.dao.insert(new Word(words[i]));

            return null;
        }
    }
}
