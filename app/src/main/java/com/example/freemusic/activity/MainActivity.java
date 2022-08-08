package com.example.freemusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freemusic.R;
import com.example.freemusic.abstracts.BaseUIActivity;

public class MainActivity extends BaseUIActivity {

    TextView textView,textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.tv_prompt);
        textView1=findViewById(R.id.tv_prompt1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PlayActivity.class));
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,OhterActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PlayActivity.playActivityWeakReference!=null&&PlayActivity.playActivityWeakReference.get() != null) {
            PlayActivity playActivity=PlayActivity.playActivityWeakReference.get();
            if (playActivity==null||playActivity.isDestroyed()||playActivity.isFinishing()){
                Toast.makeText(this, "isfinished", Toast.LENGTH_SHORT).show();
                PlayActivity.playActivityWeakReference=null;
            }
        }

    }

    @Override
    protected void initView() {

    }
}