package com.example.freemusic.model.entity;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface TabClass {

    String all = "全部";
    String album = "专辑";
    String songlist = "歌单";
    String artist = "歌手";
}
