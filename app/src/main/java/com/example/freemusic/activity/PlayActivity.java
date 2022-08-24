package com.example.freemusic.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.freemusic.R;
import com.example.freemusic.abstracts.BaseUIActivity;
import com.example.freemusic.util.MusicPlayController;

public class PlayActivity extends BaseUIActivity implements View.OnClickListener {


    private TextView tvAuthor, tvTitle, tvCountTime, tvCurrentTime;
    private ImageView imStart, imPlayNext, imPlayAfter, imOrder, imCurrentPlayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

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

        // TODO: 22-8-23 style and lyrics
    }

    @Override
    protected void initData() {

        MusicPlayController musicPlayController = new MusicPlayController();
        musicPlayController.initPlayer();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                musicPlayController.play();
            }
        }).start();

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.im_actplay_start) {

        } else if (v.getId() == R.id.im_actplay_after) {

        } else if (v.getId() == R.id.im_actplay_next) {

        } else if (v.getId() == R.id.im_actplay_order) {

        } else if (v.getId() == R.id.im_actplay_currentlist) {

        }
    }
}