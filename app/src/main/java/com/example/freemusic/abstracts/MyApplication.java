package com.example.freemusic.abstracts;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.freemusic.other.BgPlayService;

public class MyApplication extends Application {

    private static MyApplication mContext;

    public static MyApplication getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        bindService(new Intent(MyApplication.getContext(), BgPlayService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                ((BgPlayService.PlayBinder) service).initPlayer();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }

    public void setDarkMode(boolean isDark) {
        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

}
