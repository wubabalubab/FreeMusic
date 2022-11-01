package com.example.freemusic.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Looper;

import com.example.freemusic.inf.MusicPlay;
import com.example.freemusic.model.entity.MusicBean;
import com.example.freemusic.model.entity.TabClass;
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
    private QueryListHandler mQueryListHandler;

    @Override
    public void openFilePlay() {
        // TODO: 22-8-24 此处设计查询某一音乐播放
    }

    @Override
    public void initPlayer() {
        mMediaPlayer = new MediaPlayer();

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
        isPrepared = false;
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
    public void getMusicList(String type) {
        // TODO: 22-8-24 使用service后台交互
        getLocalList(type);
    }

    /**
     * return local music list
     */
    private void getLocalList(String type) {
        mMusicBeanList = new ArrayList<>();
        QueryListThread queryThread = new QueryListThread(type);
        mQueryListHandler = new QueryListHandler(Looper.getMainLooper(), new QueryListHandler.QueryListener() {
            @Override
            public void queryComplete() {
                try {
                    if (queryThread.call() != null && queryThread.call().size() > 0) {
                        mMusicBeanList.clear();
                        mMusicBeanList.addAll(queryThread.call());
                    }
//                    PlayerPrepare(mMusicBeanList.get(0));
                } catch (Exception e) {
                }
            }
        });
        queryThread.setHandler(mQueryListHandler);
        queryThread.start();
    }

    private void PlayerPrepare(MusicBean musicBean) throws IOException {
        mMediaPlayer.setDataSource(musicBean.getPath());
        mMediaPlayer.setLooping(false);
        mMediaPlayer.prepare();
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                isPrepared = true;
            }
        });
    }


}
