package com.example.nicepicbbs.adapter;


import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public abstract class EndLessOnScrollListener extends RecyclerView.OnScrollListener {
    //用来标记是否正在向最后一个滑动
    boolean isSlidingToLast = false;

    //已经加载出来的Item的数量
    private int totalItemCount;

    //主要用来存储上一个totalItemCount
    private int previousTotal = 0;

    //是否正在上拉数据
    private boolean loading = true;

    private LinearLayoutManager manager;

    private int lastVisibleItem;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
        if (dy > 0) {
            //大于0表示正在向下滚动
            isSlidingToLast = true;
        } else {
            isSlidingToLast = false;
        }
        manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        // 当不滚动时
        lastVisibleItem = manager.findLastVisibleItemPosition();

        totalItemCount = manager.getItemCount();


        if (loading) {
            Log.e("wu","加载中");
            if (totalItemCount > previousTotal) {
                Log.e("wu","加载结束 "+totalItemCount);
                //说明数据已经加载结束
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        // 判断是否滚动到底部，并且是向右滚动
        if (!loading && lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
            Log.e("wu",lastVisibleItem+" "+totalItemCount);
            Log.e("wu","要加载");
            Toast.makeText(recyclerView.getContext(), "加载了", Toast.LENGTH_SHORT).show();
            onLoadMore();
            loading = true;
        }
    }

    /**
     * 提供一个抽闲方法，在Activity中监听到这个EndLessOnScrollListener
     * 并且实现这个方法
     */
    public abstract void onLoadMore();
}

