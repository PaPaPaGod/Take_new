package com.price.take_new.utils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.takeretrofit.bean.mydeliveryorder.MyDeliveryOrderDatum;
import com.example.takeretrofit.bean.myhelpdeliveryorder.MyHelpOrderDatum;
import com.price.take_new.R;

import java.util.List;

/**
 * 我发布的快递
 * Created by price on 2/23/2017.
 */

public class PackageMyAdapter<T> extends CommonRecyclerViewAdapter  {
    public PackageMyAdapter(Context mContext, List<T> mDatas, int layoutId, String token) {
        super(mContext, mDatas, layoutId, token);
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, Object o) {
        CommonViewHolder myViewHolder = (CommonViewHolder) holder;
        MyDeliveryOrderDatum item = (MyDeliveryOrderDatum) o;
        if(o!=null){
            myViewHolder.setText(R.id.help_express_time,item.getTakeTime()).
                    setText(R.id.help_express_company,item.getCompany()).
                    setText(R.id.help_express_place,item.getAddress())
                    .setText(R.id.help_express_description,item.getDes());
            myViewHolder.setVisibility(R.id.help_express_name,View.GONE);
//            Log.e("getName",item.getName());
            //我的快递
            Log.e("status",item.getStatus());
            switch (Integer.parseInt(item.getStatus())){
                case 0:
                    myViewHolder.setText(R.id.tv_express_state,"进行中");
                    break;
                case 1:
                    myViewHolder.setText(R.id.tv_express_state,"已过期");
                    break;
                case 2:
                    myViewHolder.setText(R.id.tv_express_state,"已完成");
                    break;
            }
        }else{
            View view = holder.itemView.findViewById(R.id.footer_load_more);
            loadMoreListener.setLoad(view);
        }
    }
}
