package com.example.freemusic.activity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.freemusic.R;
import com.example.freemusic.abstracts.BaseUIActivity;
import com.example.freemusic.adapter.ViewPagerAdapter;
import com.example.freemusic.helper.MainActivityLifeCycle;
import com.example.freemusic.model.entity.TabClass;
import com.example.freemusic.view.VpFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseUIActivity {


    ViewPager2 mVpMain;
    TabLayout mTabLayout;
    private ViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        getLifecycle().addObserver(new MainActivityLifeCycle());
        super.onCreate(savedInstanceState);
        checkPermission();
    }

    private void checkPermission() {
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
        mVpMain.setCurrentItem(0);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

    }

    @Override
    protected void initView() {

        mVpMain = findViewById(R.id.vp_act_main);
        mTabLayout = findViewById(R.id.tab_act_main);

        List<Fragment> tabFragmentList = new ArrayList<>();
        List<String> tabTitles = new ArrayList<>();
        tabTitles.add(TabClass.all);
        tabTitles.add(TabClass.songlist);
        tabTitles.add(TabClass.album);
        tabTitles.add(TabClass.artist);
        for (int i = 0; i < tabTitles.size(); i++) {
            VpFragment fragment = VpFragment.newInstance(tabTitles.get(i));
            tabFragmentList.add(fragment);
        }
        viewPagerAdapter = new ViewPagerAdapter(this, tabFragmentList);
        mVpMain.setAdapter(viewPagerAdapter);
        mVpMain.setUserInputEnabled(true);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(mTabLayout, mVpMain, (tab, position)
                -> tab.setText(tabTitles.get(position)));
        tabLayoutMediator.attach();
    }

    @Override
    protected void initData() {

    }

}