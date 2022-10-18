package com.example.freemusic.model.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

import com.example.freemusic.abstracts.MyApplication;

public class MusicListViewModelHelper {

    private static MusicListViewModel instance;

    public static MusicListViewModel getInstance() {
        if (instance == null) {
            synchronized (MusicListViewModelHelper.class) {
                instance = ViewModelProvider.AndroidViewModelFactory.getInstance(MyApplication.getContext()).create(MusicListViewModel.class);
            }
        }
        return instance;
    }
}
