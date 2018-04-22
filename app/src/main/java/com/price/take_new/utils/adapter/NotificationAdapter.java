package com.price.take_new.utils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.takeretrofit.bean.sysnotification.NotificationDatum;
import com.price.take_new.Constant;
import com.price.take_new.R;

import java.util.List;

/**
 * Created by price on 3/14/2017.
 */

public class NotificationAdapter<T> extends CommonRecyclerViewAdapter {
    public NotificationAdapter(Context mContext, List mDatas, int layoutId, String token) {
        super(mContext, mDatas, layoutId, token);
    }

    private int ADDFRIEND = 100;
    private int NOTIFICAITON =101;

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, Object o) {
        CommonViewHolder viewHolder = (CommonViewHolder) holder;
        NotificationDatum datum = (NotificationDatum) o;
        if(datum.getStyle()==1) {
            viewHolder.setVisibility(R.id.rl_friend, View.GONE);
            viewHolder.setVisibility(R.id.rl_msg, View.VISIBLE);
            viewHolder.setText(R.id.tv_notification_msg_name,"xxxx");
        }
        else{
            viewHolder.setVisibility(R.id.rl_msg, View.GONE);
            viewHolder.setVisibility(R.id.rl_friend, View.VISIBLE);
            viewHolder.setText(R.id.tv_notification_add_name,"xxxx");
        }
        viewHolder.setText(R.id.tv_notification_time,"2018-3-16");
        //            viewHolder.setText(R.id.notification_name,datum.getOther_name())
//                    .setText(R.id.notification_content,datum.getContent()+"，点击查看详细快递消息")
//                    .setText(R.id.notification_time,datum.getCreated());
//        if(datum!=null){
//            viewHolder.setText(R.id.notification_name,datum.getOther_name())
//                    .setText(R.id.notification_content,datum.getContent()+"，点击查看详细快递消息")
//                    .setText(R.id.notification_time,datum.getCreated());
//        }
    }
}
