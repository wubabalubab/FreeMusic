package com.example.freemusic.model.viewmodel;

import android.app.Application;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.freemusic.model.entity.MusicBean;

import java.util.ArrayList;
import java.util.List;

public class MusicListViewModel extends AndroidViewModel {
    private MutableLiveData<List<MusicBean>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<MusicBean>> artistList = new MutableLiveData<>();
    private MutableLiveData<List<MusicBean>> songList = new MutableLiveData<>();
    private MutableLiveData<List<MusicBean>> albumList = new MutableLiveData<>();

    public MusicListViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<MusicBean>> getLiveData() {
        return mutableLiveData;
    }


    public void setList(List<MusicBean> list) {
        if (mutableLiveData.getValue() != null) {
            mutableLiveData.getValue().clear();
        }
        if (Looper.myLooper()==Looper.getMainLooper()) {
            mutableLiveData.setValue(new ArrayList<>(list));
        }else {
            mutableLiveData.postValue(new ArrayList<>(list));
        }
    }

    @UiThread
    public void addList(List<MusicBean> list) {
        if (mutableLiveData.getValue() == null) {
            mutableLiveData.setValue(new ArrayList<>());
        }
        mutableLiveData.getValue().addAll(list);
    }
}
