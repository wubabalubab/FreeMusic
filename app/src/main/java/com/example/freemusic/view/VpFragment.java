package com.example.freemusic.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freemusic.R;
import com.example.freemusic.abstracts.LazyFragment;
import com.example.freemusic.adapter.VpFgListAdapter;
import com.example.freemusic.model.entity.MusicBean;
import com.example.freemusic.model.viewmodel.MusicListViewModel;
import com.example.freemusic.model.viewmodel.MusicListViewModelHelper;

import java.util.ArrayList;
import java.util.List;


public class VpFragment extends LazyFragment {


    private List<MusicBean> musicBeanList = new ArrayList<>();
    private RecyclerView recyclerView;
    private VpFgListAdapter listAdapter;
    public VpFragment() {
    }

    public static VpFragment newInstance(String param1, String param2) {
        VpFragment fragment = new VpFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_vpfg_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listAdapter = new VpFgListAdapter(getContext(), musicBeanList);
        recyclerView.setAdapter(listAdapter);

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void loadData() {
        MusicListViewModel musicListViewModel = MusicListViewModelHelper.getInstance();
        musicListViewModel.getLiveData().observe(this, new Observer<List<MusicBean>>() {
            @Override
            public void onChanged(List<MusicBean> list) {
                musicBeanList.addAll(list);
                listAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vp, container, false);
    }
}