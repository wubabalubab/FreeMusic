package com.example.freemusic.activity;

import android.os.Bundle;

import com.example.freemusic.R;
import com.example.freemusic.abstracts.BaseUIActivity;

import java.lang.ref.WeakReference;

public class PlayActivity extends BaseUIActivity {

    public static WeakReference<PlayActivity> playActivityWeakReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        playActivityWeakReference=new WeakReference<PlayActivity>(this);

    }

    @Override
    protected void initView() {

    }
}