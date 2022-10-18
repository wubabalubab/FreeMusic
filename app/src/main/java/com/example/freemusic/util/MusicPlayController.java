package com.example.freemusic.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.example.freemusic.abstracts.MyApplication;
import com.example.freemusic.inf.MusicPlay;
import com.example.freemusic.model.entity.MusicBean;
import com.example.freemusic.model.viewmodel.MusicListViewModel;
import com.example.freemusic.model.viewmodel.MusicListViewModelHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MusicPlayController implements MusicPlay {

    MusicListViewModel musicListViewModel = MusicListViewModelHelper.getInstance();
    private List<MusicBean> mMusicBeanList;
    private boolean isPrepared;
    private MediaPlayer mMediaPlayer;

    @Override
    public void openFilePlay() {
        // TODO: 22-8-24 此处设计查询某一音乐播放
    }

    @Override
    public void initPlayer() {

        // TODO: 22-8-24 使用service后台交互
        getLocalList();
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(mMusicBeanList.get(0).getPath());
            mMediaPlayer.setLooping(false);
            mMediaPlayer.prepare();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    isPrepared = true;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeOrder(int orderType) {

    }

    @Override
    public boolean isPlay() {
        return mMediaPlayer.isPlaying();
    }

    @Override
    public void play() {
        // TODO: 22-8-24 这里要考虑和prepare异步的回调
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        } else {
            mMediaPlayer.start();
        }
    }

    @Override
    public void stop() {
        mMediaPlayer.stop();
    }

    @Override
    public void destroy() {
        // TODO: 22-8-24 考虑销毁时机与销毁什么资源
        if (mMediaPlayer != null) {
            mMediaPlayer = null;
        }
    }

    private void openfile(Context context) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open("yumou.mp3");
            AssetFileDescriptor assetFileDescriptor = assetManager.openFd("yumou.mp3");
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                    assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mediaPlayer.setLooping(false);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
            //            mediaPlayer.setAudioAttributes(AudioAttributes.CONTENT_TYPE_MUSIC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MusicBean> getMusicList() {
        return getLocalList();
    }

    /**
     * @return return local music list
     */
    private List<MusicBean> getLocalList() {
        // TODO: 22-8-24 这里的处理应当开启后台线程处理
        mMusicBeanList = new ArrayList<>();
        Uri collection;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            collection = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            // TODO: 22-8-24 没有实际测试 Android 10版本下的效果
            collection = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        }
        Cursor cursor = MyApplication.getContext().getContentResolver().query(collection,
                null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                MusicBean musicBean = new MusicBean();
                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                // TODO: 22-8-24 未对合适大小数据进行过滤
                if (!TextUtils.isEmpty(name)) {
                    musicBean.setName(name);
                }
                musicBean.setId(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)));
                musicBean.setAuthor(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                musicBean.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                musicBean.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
                musicBean.setSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));
                musicBean.setAlbumId(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)));
                musicBean.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));
                mMusicBeanList.add(musicBean);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        musicListViewModel.setList(mMusicBeanList);
        return mMusicBeanList;
    }
}
