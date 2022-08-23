package com.example.freemusic.inf;

import android.content.Context;

import com.example.freemusic.model.MusicBean;

import java.util.List;

public interface MusicPlay {

    void openFilePlay();
    void play();
    void pause();
    void destroy();
    List<MusicBean> getMusicList();
    void initPlayer();
    void changeOrder(int orderType);
}
