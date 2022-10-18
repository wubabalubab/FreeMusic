package com.example.freemusic.other;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

import com.example.freemusic.abstracts.MyApplication;
import com.example.freemusic.inf.MusicPlay;
import com.example.freemusic.model.entity.MusicBean;
import com.example.freemusic.util.MusicPlayController;

import java.util.List;

public class BgPlayService extends Service {
    public static boolean isRunning = false;
    // TODO: 22-9-8 此处的单例有问题
    private static BgPlayService instance;
    private static PlayBinder mPlayBinder;

    public BgPlayService() {
    }

    public static Binder getBinderInstance() {
        if (mPlayBinder != null) {
            return mPlayBinder;
        }
        BgPlayService bgPlayService = new BgPlayService();
        bgPlayService.getApplication().bindService(new Intent(MyApplication.getContext(), BgPlayService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mPlayBinder = (PlayBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
        return mPlayBinder;
    }

    @Override
    public IBinder onBind(Intent intent) {
        isRunning = true;
        mPlayBinder = new PlayBinder();
        return mPlayBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static class PlayBinder extends Binder implements MusicPlay {

        MusicPlayController musicPlayController = new MusicPlayController();


        @Override
        public void openFilePlay() {
            musicPlayController.openFilePlay();
        }

        @Override
        public void play() {
            musicPlayController.play();
        }

        @Override
        public void stop() {
            musicPlayController.stop();
        }

        @Override
        public void destroy() {
            musicPlayController.destroy();
        }

        @Override
        public List<MusicBean> getMusicList() {
            return musicPlayController.getMusicList();
        }

        @Override
        public void initPlayer() {
            musicPlayController.initPlayer();
        }

        @Override
        public void changeOrder(int orderType) {
            musicPlayController.changeOrder(orderType);
        }

        @Override
        public boolean isPlay() {
            return musicPlayController.isPlay();
        }
    }

}