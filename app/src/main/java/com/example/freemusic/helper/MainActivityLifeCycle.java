package com.example.freemusic.helper;

import android.Manifest;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public class MainActivityLifeCycle implements DefaultLifecycleObserver {

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onCreate(owner);


    }

}
