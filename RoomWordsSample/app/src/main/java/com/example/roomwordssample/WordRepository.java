package com.example.roomwordssample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

public class WordRepository {

    private WordDao mWordDao;

    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }
}
