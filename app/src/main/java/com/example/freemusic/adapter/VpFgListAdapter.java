package com.example.freemusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freemusic.R;
import com.example.freemusic.model.entity.MusicBean;

import java.util.List;

public class VpFgListAdapter extends RecyclerView.Adapter<VpFgListAdapter.ViewHolder> {
    private final Context context;
    private final List<MusicBean> musicBeanList;

    public VpFgListAdapter(Context context, List<MusicBean> musicBeanList) {
        this.context = context;
        this.musicBeanList = musicBeanList;
    }

    @NonNull
    @Override
    public VpFgListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_vpfg_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VpFgListAdapter.ViewHolder holder, int position) {
        MusicBean musicBean = musicBeanList.get(holder.getLayoutPosition());
        StringBuilder builder=new StringBuilder();
        if (musicBean.getName() != null) {
            builder.append(musicBean.getName());
        }
        if (musicBean.getAuthor() != null) {
            builder.append(musicBean.getAuthor());
        }
        if (musicBean.getAlbum() != null) {
            builder.append(musicBean.getAlbum());
        }
        builder.append(musicBean.getAlbumId());
        holder.tvTitle.setText(builder);
    }


    @Override
    public int getItemCount() {
        return musicBeanList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_vpfg_title);
        }
    }
}
