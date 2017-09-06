package com.price.take_new.utils.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by intel on 9/5/2017.
 */

public abstract class ScrollRecyclerViewListener extends RecyclerView.OnScrollListener{
    //声明一个LinearLayoutManager
    private LinearLayoutManager mLinearLayoutManager;

    //当前页，从0开始
    private int currentPage = 0;

    //已经加载出来的Item的数量
    private int totalItemCount;

    //主要用来存储上一个totalItemCount
    private int previousTotal = 0;

    //在屏幕上可见的item数量
    private int visibleItemCount;

    //在屏幕可见的Item中的第一个
    private int firstVisibleItem;

    //是否正在上拉数据
    private boolean isLoading = true;

    private boolean loadFinish = true;

    public void setloadFinish(boolean loadFinish) {
        this.loadFinish = loadFinish;
    }

    public ScrollRecyclerViewListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        if(isLoading){
            if(loadFinish){
                //说明数据已经加载结束
                isLoading = false;
                previousTotal = totalItemCount;
                return;
            }
        }
        //这里需要好好理解
        if (!isLoading && totalItemCount-visibleItemCount <= firstVisibleItem){
            Log.e("scroll",currentPage+"");
            currentPage++;
            onLoadMore(currentPage);
            isLoading = true;
//            loadFinish = false;
        }
    }

    /**
     * 提供一个抽闲方法，在Activity中监听到这个EndLessOnScrollListener
     * 并且实现这个方法
     * */
    public abstract void onLoadMore(int currentPage);
}
