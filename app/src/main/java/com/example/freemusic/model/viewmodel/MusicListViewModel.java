package com.example.freemusic.model.viewmodel;

import android.app.Application;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.freemusic.model.entity.MusicBean;
import com.example.freemusic.model.entity.TabClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MusicListViewModel extends AndroidViewModel {
    private final MutableLiveData<List<MusicBean>> allList = new MutableLiveData<>();
    private final MutableLiveData<List<MusicBean>> artistList = new MutableLiveData<>();
    private final MutableLiveData<List<MusicBean>> songList = new MutableLiveData<>();
    private final MutableLiveData<List<MusicBean>> albumList = new MutableLiveData<>();

    public MusicListViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<MusicBean>> getLiveData(String type) {
        switch (type) {

            case TabClass.songlist:
                return songList;
            case TabClass.album:
                return albumList;
            case TabClass.artist:
                return artistList;
            case TabClass.all:
            default:
                return allList;
        }
    }


    public void setList(List<MusicBean> list, String type) {

        switch (type) {
            case TabClass.all:
                setList_(allList, list);
                break;
            case TabClass.songlist:
                setList_(songList, list);
                break;
            case TabClass.album:
                setList_(albumList, list);
                break;
            case TabClass.artist:
                setList_(artistList, list);
                break;
            default:
                break;
        }
    }

    private void setList_(MutableLiveData<List<MusicBean>> list, List<MusicBean> beanList) {
        if (list.getValue() != null) {
            list.getValue().clear();
        }

        if (!Objects.deepEquals(list.getValue(), beanList)) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                list.setValue(new ArrayList<>(beanList));
            } else {
                list.postValue(new ArrayList<>(beanList));
            }
        }
    }

    @UiThread
    public void addList(List<MusicBean> list, String type) {
        switch (type) {
            case TabClass.all:
                addList_(allList, list);
                break;
            case TabClass.songlist:
                addList_(songList, list);
                break;
            case TabClass.album:
                addList_(albumList, list);
                break;
            case TabClass.artist:
                addList_(artistList, list);
                break;
            default:
                break;
        }
    }

    private void addList_(MutableLiveData<List<MusicBean>> list, List<MusicBean> beanList) {
        if (list.getValue() == null) {
            list.setValue(new ArrayList<>());
        }
        list.getValue().addAll(beanList);
    }
}
