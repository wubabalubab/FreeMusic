package com.example.freemusic.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityOptionsCompat;

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

        ActivityResultLauncher<String> resultRegistry=registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {

                }
            }
        });
        resultRegistry.launch(Manifest.permission.READ_EXTERNAL_STORAGE);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}