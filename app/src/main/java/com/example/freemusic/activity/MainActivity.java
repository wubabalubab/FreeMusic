package com.example.freemusic.activity;

import android.Manifest;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.freemusic.R;
import com.example.freemusic.abstracts.BaseUIActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseUIActivity {


    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    TextView tvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        ActivityResultLauncher<String> resultRegistry = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
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
    public void onBackPressed() {
//        super.onBackPressed();
        if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.closeDrawers();
        }
    }


    @Override
    protected void initView() {
        mDrawerLayout = findViewById(R.id.drawer_actmain);
        mNavigationView = findViewById(R.id.navigation_actmain);
        tvBack = findViewById(R.id.tv_act_main_back);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();

                return true;
            }
        });
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawers();
                }
                if (!MainActivity.this.isFinishing()) {
                    finish();
                }
            }
        });
    }

    @Override
    protected void initData() {
    }
}