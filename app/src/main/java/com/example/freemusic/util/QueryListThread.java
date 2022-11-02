package com.example.freemusic.util;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import com.example.freemusic.abstracts.MyApplication;
import com.example.freemusic.model.entity.MusicBean;
import com.example.freemusic.model.entity.TabClass;
import com.example.freemusic.model.viewmodel.MusicListViewModel;
import com.example.freemusic.model.viewmodel.MusicListViewModelHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class QueryListThread extends Thread implements Callable<List<MusicBean>> {

    public static final long MIN_DURATION = 30000;
    private final String type;
    private final List<MusicBean> beanList = new ArrayList<>();
    private QueryListHandler handler;
    private String[] queryColumns;

    public QueryListThread(String type) {
        this.type = type;
    }

    public void setHandler(QueryListHandler handler) {
        if (handler != null) {
            this.handler = handler;
        }
    }

    @Override
    public void run() {
        super.run();
        Uri collectionUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collectionUri = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            // TODO: 22-8-24 没有实际测试 Android 10版本下的效果
            collectionUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        }
        switch (type) {
            case TabClass.all:
                queryColumns = new String[]{MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media._ID, MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.ALBUM};
                break;
            case TabClass.album:
                queryColumns = new String[]{MediaStore.Audio.Media.DURATION,MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.ALBUM};
                break;
            case TabClass.artist:
                queryColumns = new String[]{MediaStore.Audio.Media.DURATION,MediaStore.Audio.Media.ARTIST};
                break;
            case TabClass.songlist:
                // TODO: 22-10-31 自定义歌单
                queryColumns = new String[]{};
                break;
            default:
                break;
        }
        Cursor cursor = MyApplication.getContext().getContentResolver().query(collectionUri,
                queryColumns, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (beanList.size() > 0) {
            beanList.clear();
        }
        if (cursor != null) {
            looper:
            while (cursor.moveToNext()) {
                MusicBean musicBean = new MusicBean();
                // TODO: 22-8-24 未对合适大小数据进行过滤
                long duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                if (duration < QueryListThread.MIN_DURATION) {
                    continue;
                }
                if (TextUtils.equals(type, TabClass.all)) {
                    musicBean.setAuthor(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                    musicBean.setAlbumId(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)));
                    musicBean.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));
                    musicBean.setName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
                    musicBean.setId(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)));
                    musicBean.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                    musicBean.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
                    musicBean.setSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));
                }
                if (TextUtils.equals(TabClass.artist, type)) {
                    String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                    for (MusicBean bean : beanList) {
                        if (TextUtils.equals(bean.getAuthor(), artist)) {
                            break looper;
                        }
                    }
                    musicBean.setAuthor(artist);
                }
                if (TextUtils.equals(type, TabClass.album)) {
                    long albumId = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                    for (MusicBean bean : beanList) {
                        if (bean.getAlbumId() == albumId) {
                            break looper;
                        }
                    }
                    musicBean.setAlbumId(albumId);
                    musicBean.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));
                }
                beanList.add(musicBean);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        MusicListViewModel musicListViewModel = MusicListViewModelHelper.getInstance();
        musicListViewModel.setList(beanList, type);
        Message message = new Message();
        message.what = 100;
        handler.handleMessage(message);
    }

    @Override
    public List<MusicBean> call() {
        return beanList;
    }
}