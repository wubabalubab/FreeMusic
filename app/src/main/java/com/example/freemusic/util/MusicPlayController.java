package com.example.freemusic.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;

import com.example.freemusic.inf.MusicPlay;

import java.io.IOException;
import java.io.InputStream;

public class MusicPlayController implements MusicPlay {

    @Override
    public void openFile() {

    }

    @Override
    public void play() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    private void openfile(Context context) {
        AssetManager assetManager=context.getAssets();
        try {
            InputStream inputStream=assetManager.open("yumou.mp3");
            AssetFileDescriptor assetFileDescriptor=assetManager.openFd("yumou.mp3");
            MediaPlayer mediaPlayer=new MediaPlayer();
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                    assetFileDescriptor.getStartOffset(),assetFileDescriptor.getLength());
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
}
