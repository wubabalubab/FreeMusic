package com.example.freemusic.abstracts;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

public abstract class BaseUIActivity extends AppCompatActivity {

    private int mDarDkMode;
    protected  String TAG = "BaseUIActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        TAG=this.getClass().getSimpleName();
        super.onCreate(savedInstanceState);
        mDarDkMode = isDarkMode();
        initView();
        initData();
    }

    protected abstract void initView();
    protected abstract void initData();

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int sysThemeConfig = newConfig.uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (sysThemeConfig != mDarDkMode) {
            recreate();
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private int isDarkMode() {
        return (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK);
    }

}
