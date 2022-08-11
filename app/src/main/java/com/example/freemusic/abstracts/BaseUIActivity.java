package com.example.freemusic.abstracts;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

public abstract class BaseUIActivity extends AppCompatActivity {

    private int mDarDkMode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDarDkMode = isDarkMode();
        initView();
    }

    protected abstract void initView();

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
