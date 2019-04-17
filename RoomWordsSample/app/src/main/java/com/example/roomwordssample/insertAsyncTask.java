package com.example.roomwordssample;

import android.os.AsyncTask;

public class insertAsyncTask extends AsyncTask<Word, Void, Void> {

    private WordDao mAsyncTaskDao;

    insertAsyncTask(WordDao dao) {
        mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Word... params) {
        mAsyncTaskDao.insert(params[0]);
        return null;
    }
}