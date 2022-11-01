package com.example.freemusic.inf;

import com.example.freemusic.model.entity.MusicBean;

import java.util.List;

public interface MusicPlay {

    void openFilePlay();

    void play();

    void stop();

    void destroy();

    void getMusicList(String type);

    void initPlayer();

    void changeOrder(int orderType);

    boolean isPlay();


}
