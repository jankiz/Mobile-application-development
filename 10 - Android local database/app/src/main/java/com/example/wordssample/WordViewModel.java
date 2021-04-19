package com.example.wordssample;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository repository;
    private LiveData<List<Word>> words;

    public WordViewModel(Application application) {
        super(application);

        this.repository = new WordRepository(application);
        this.words = repository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return this.words;
    }

    public void insert(Word word) {
        this.repository.insert(word);
    }
}
