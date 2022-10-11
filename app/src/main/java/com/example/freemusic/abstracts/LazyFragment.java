package com.example.freemusic.abstracts;

import androidx.fragment.app.Fragment;

public abstract class LazyFragment extends Fragment {

    private boolean isLoaded = false;

    @Override
    public void onResume() {
        super.onResume();
        if (!isLoaded) {
            loadData();
        }
        isLoaded = true;
    }

    protected abstract void loadData();
}
