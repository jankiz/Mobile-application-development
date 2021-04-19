package com.example.wordssample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDAO {

    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    //@Delete
    // void delete(Word word);

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getWords();
}
