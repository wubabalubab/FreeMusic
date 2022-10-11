package com.example.freemusic.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.freemusic.R;
import com.example.freemusic.abstracts.BaseUIActivity;
import com.example.freemusic.adapter.ViewPagerAdapter;
import com.example.freemusic.model.TabClass;
import com.example.freemusic.view.VpFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseUIActivity {


    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    TextView tvBack;
    ViewPager2 mVpMain;
    TabLayout mTabLayout;
    private ViewPagerAdapter viewPagerAdapter;


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
        mVpMain.setCurrentItem(0);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

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
        mVpMain = findViewById(R.id.vp_act_main);
        mTabLayout = findViewById(R.id.tab_act_main);

        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        // TODO: 22-9-27 和viewpager2的手势冲突处理
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                if (item.getItemId() == R.id.item_menu_play) {
                    MainActivity.this.startActivity(new Intent(MainActivity.this, PlayActivity.class));
                }
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
        List<Fragment> tabFragmentList = new ArrayList<>();
        List<String> tabTitles = new ArrayList<>();
        tabTitles.add(TabClass.all);
        tabTitles.add(TabClass.album);
        tabTitles.add(TabClass.songlist);
        tabTitles.add(TabClass.artist);
        for (int i = 0; i < tabTitles.size(); i++) {
            tabFragmentList.add(new VpFragment());
        }
        viewPagerAdapter = new ViewPagerAdapter(this, tabFragmentList);
        mVpMain.setAdapter(viewPagerAdapter);
        mVpMain.setUserInputEnabled(true);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(mTabLayout, mVpMain, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitles.get(position));
            }
        });
        tabLayoutMediator.attach();
    }

    @Override
    protected void initData() {

    }

}