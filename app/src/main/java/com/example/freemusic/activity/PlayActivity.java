package com.example.freemusic.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.example.freemusic.R;
import com.example.freemusic.abstracts.BaseUIActivity;
import com.example.freemusic.other.BgPlayService;

public class PlayActivity extends BaseUIActivity implements View.OnClickListener {


    private TextView tvAuthor, tvTitle, tvCountTime, tvCurrentTime;
    private ImageView imStart, imPlayNext, imPlayAfter, imOrder, imCurrentPlayList;
    // TODO: 22-9-7 通知是否准备好
    private boolean isPrepare;
    private BgPlayService.PlayBinder mService;
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = (BgPlayService.PlayBinder) service;
            mService.initPlayer();
            changImStartBg();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_play);
        super.onCreate(savedInstanceState);

    }

    private void changImStartBg() {
        if (mService.isPlay()) {
            imStart.setBackground(ResourcesCompat.getDrawable(PlayActivity.this.getResources(), R.drawable.icon_playpause, null));
        } else {
            imStart.setBackground(ResourcesCompat.getDrawable(PlayActivity.this.getResources(), R.drawable.icon_playstart, null));
        }
    }


    @Override
    protected void initView() {
        tvAuthor = findViewById(R.id.tv_actPlay_author);
        tvTitle = findViewById(R.id.tv_actPlay_title);
        tvCountTime = findViewById(R.id.tv_actPlay_countTime);
        tvCurrentTime = findViewById(R.id.tv_actPlay_currentTime);
        imStart = findViewById(R.id.im_actplay_start);
        imPlayAfter = findViewById(R.id.im_actplay_after);
        imPlayNext = findViewById(R.id.im_actplay_next);
        imOrder = findViewById(R.id.im_actplay_order);
        imCurrentPlayList = findViewById(R.id.im_actplay_currentlist);

        imStart.setOnClickListener(this);
        imPlayAfter.setOnClickListener(this);
        imPlayNext.setOnClickListener(this);
        imOrder.setOnClickListener(this);
        imCurrentPlayList.setOnClickListener(this);


        // TODO: 22-8-23 style and lyrics
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void initData() {

        if (!BgPlayService.isRunning) {
            bindService(new Intent(PlayActivity.this, BgPlayService.class), serviceConnection, BIND_AUTO_CREATE);
        } else {
            mService = (BgPlayService.PlayBinder) BgPlayService.getBinderInstance();
            changImStartBg();
        }

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.im_actplay_start) {
            mService.play();
            changImStartBg();
        } else if (v.getId() == R.id.im_actplay_after) {

        } else if (v.getId() == R.id.im_actplay_next) {

        } else if (v.getId() == R.id.im_actplay_order) {

        } else if (v.getId() == R.id.im_actplay_currentlist) {

        }
    }
}