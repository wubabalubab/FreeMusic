package com.example.freemusic.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freemusic.R;
import com.example.freemusic.abstracts.LazyFragment;
import com.example.freemusic.adapter.VpFgListAdapter;
import com.example.freemusic.model.entity.MusicBean;
import com.example.freemusic.other.BgPlayService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class VpFragment extends LazyFragment {

    private static final String TYPE = "TYPE";
    private String type;
    private List<MusicBean> musicBeanList = new ArrayList<>();
    private RecyclerView recyclerView;
    private VpFgListAdapter listAdapter;
    private BgPlayService.PlayBinder mServiceBinder;

    public VpFragment() {
    }

    public static VpFragment newInstance(String type) {
        VpFragment fragment = new VpFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(TYPE);
        }
        mServiceBinder = (BgPlayService.PlayBinder) BgPlayService.getBinderInstance();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_vpfg_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listAdapter = new VpFgListAdapter(getContext(), musicBeanList);
        recyclerView.setAdapter(listAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        List<MusicBean> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(new MusicBean(new Random().toString()));
        }
        musicBeanList.addAll(list);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void loadData() {
//        mServiceBinder.getMusicList(type);
//        MusicListViewModel musicListViewModel = MusicListViewModelHelper.getInstance();
//        musicListViewModel.getLiveData(type).observe(this, list -> {
//            Log.e("TAG", "onChanged:fg " + list.size());
//            if (musicBeanList.size() > 0) {
//                musicBeanList.clear();
//            }
//            musicBeanList.addAll(list);
//            listAdapter.notifyDataSetChanged();
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vp, container, false);
    }
}