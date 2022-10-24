package com.example.freemusic.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

public class QueryListHandler extends Handler {
    private static final int COMPLETE_QUERY = 100;
    private QueryListener queryListener;

    public QueryListHandler(@NonNull Looper looper, QueryListener queryListener) {
        super(looper);
        this.queryListener = queryListener;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (msg.what == COMPLETE_QUERY) {
            if (queryListener != null) {
                queryListener.queryComplete();
            }
        }
    }

    public interface QueryListener {
        void queryComplete();
    }
}
