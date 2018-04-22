package com.price.take_new.utils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.price.take_new.R;
import com.price.take_new.utils.listener.LoadMoreListener;
import com.price.take_new.utils.listener.RVOnItemClickListener;

import java.util.List;

/**
 * Created by price on 2016/11/11.
 */

public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    protected static final int TYPE_FOOTER = 1;

    protected Context mContext;
    protected List<T> mDatas;
    protected String token;
    protected LayoutInflater inflater;
    protected RVOnItemClickListener listener;
    protected int layoutId;
    protected LoadMoreListener loadMoreListener;
//    protected MutipleItemTypeListner<T> itemTypeListner;

    public CommonRecyclerViewAdapter(Context mContext, List<T> mDatas, int layoutId, String token) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.token = token;
        this.layoutId = layoutId;
        this.inflater = LayoutInflater.from(mContext);
    }

    public void setOnItemListener(RVOnItemClickListener listener) {
        this.listener = listener;
    }

    public abstract void convert(RecyclerView.ViewHolder holder, T t);

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(position<mDatas.size()){
            convert(holder,mDatas.get(position));
        } else{
            convert(holder,null);
        }
        if(listener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
//            View view = inflater.inflate(R.layout.item_footer, parent, false);
            return CommonViewHolder.getCommonViewHolder(mContext,
                    R.layout.item_footer, parent,token);
        } else {
//            View view = inflater.inflate(R.layout.item_main_express, parent, false);
            return CommonViewHolder.getCommonViewHolder(mContext,
                    layoutId,parent,token);
        }
    }

    //是底部iewType为TYPE_FOOTER，不是底部viewType为TYPE_ITEM
    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount()-1) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size()+1;
    }

    public void clear(){
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void setLoadMoreListener(LoadMoreListener listener) {
        this.loadMoreListener = listener;
    }

}
