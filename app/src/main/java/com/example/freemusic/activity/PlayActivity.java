package com.example.freemusic.activity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import com.example.freemusic.R;
import com.example.freemusic.abstracts.BaseUIActivity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

public class PlayActivity extends BaseUIActivity {

    public static WeakReference<PlayActivity> playActivityWeakReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        playActivityWeakReference=new WeakReference<PlayActivity>(this);

        openfile();
    }

    private void openfile() {
        AssetManager assetManager=this.getAssets();
        try {
            InputStream inputStream=assetManager.open("yumou.mp3");
//            File file=new File();
//            Log.e(TAG, "openfile: "+file.length() );
            Log.e(TAG, "openfile: "+inputStream.available() );
//            inputStream.
            AssetFileDescriptor assetFileDescriptor=assetManager.openFd("yumou.mp3");
            MediaPlayer mediaPlayer=new MediaPlayer();
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),assetFileDescriptor.getStartOffset(),assetFileDescriptor.getLength());
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

    private static final String TAG = "PlayActivity";
    @Override
    protected void initView() {

    }
}