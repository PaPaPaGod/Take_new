package com.price.take_new.utils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.takeretrofit.bean.friend.FriendDatum;
import com.price.take_new.R;
import com.price.take_new.utils.listener.FriendClickListener;

import java.util.List;

/**
 * Created by price on 2016/11/12.
 */

public class FriendListAdapter<T> extends CommonRecyclerViewAdapter {
    private FriendClickListener listener;

    public void setListener(FriendClickListener listener) {
        this.listener = listener;
    }

    public FriendListAdapter(Context mContext, List<T> mDatas, int layoutId, String token) {
        super(mContext, mDatas,layoutId,token);
    }

    @Override
    public void convert(final RecyclerView.ViewHolder holder, Object o) {
        CommonViewHolder myViewHolder = (CommonViewHolder) holder;
        if(o!=null){
            FriendDatum item = (FriendDatum) o;
//            myViewHolder.setText(R.id.tv_item_friend_name,item.getName())
//                    .setText(R.id.tv_item_friend_count,item.getId());
            String sex = "男";
            if(sex.equals("男")){
                myViewHolder.setImageResource(R.id.iv_item_friend_portrait,R.drawable.portrait_male);
            } else {
                myViewHolder.setImageResource(R.id.iv_item_friend_portrait,R.drawable.portrait_female);
            }
            if(listener!=null){
                myViewHolder.addOnClickListener(R.id.btn_item_friend_sendmsg,listener);
            }
        }
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return CommonViewHolder.getCommonViewHolder(mContext,
                    layoutId,parent,token);
    }

}
