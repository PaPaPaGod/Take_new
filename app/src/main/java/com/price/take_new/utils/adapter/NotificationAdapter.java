package com.price.take_new.utils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.takeretrofit.bean.sysnotification.NotificationDatum;
import com.price.take_new.R;
import com.price.take_new.utils.item.SysNotificationItem;

import java.util.List;

/**
 * Created by price on 3/14/2017.
 */

public class NotificationAdapter<T> extends CommonRecyclerViewAdapter {
    public NotificationAdapter(Context mContext, List mDatas, int layoutId, String token) {
        super(mContext, mDatas, layoutId, token);
    }

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
        if(datum!=null){
            viewHolder.setText(R.id.notification_name,datum.getOther_name())
                    .setText(R.id.notification_content,datum.getContent()+"，点击查看详细快递消息")
                    .setText(R.id.notification_time,datum.getCreated());
        }
    }
}
