package com.price.take_new.utils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;


import com.example.takeretrofit.bean.delivery.DeliveryDatum;
import com.price.take_new.R;
import com.price.take_new.utils.item.AllExpressOrderItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by price on 2016/11/12.
 */

public class RVPackageAdapter<T> extends CommonRecyclerViewAdapter {
//    private SetPortraitListener portraitListener;
//
//    public void setPortraitListener(SetPortraitListener portraitListener) {
//        this.portraitListener = portraitListener;
//    }

    public RVPackageAdapter(Context mContext, List<T> mDatas,int layoutId,String token) {
        super(mContext, mDatas,layoutId,token);
    }

    @Override
    public void convert(final RecyclerView.ViewHolder holder, Object o) {
        CommonViewHolder myViewHolder = (CommonViewHolder) holder;
        if(o!=null){
            DeliveryDatum item = (DeliveryDatum) o;
//            Log.e("rv_adapter",item.getName());
            myViewHolder.setText(R.id.main_express_item_userName,item.getName())
                    .setText(R.id.main_express_item_place,item.getAddress())
                    .setText(R.id.main_express_item_time,item.getTakeTime())
                    .setText(R.id.main_express_item_description,item.getDes())
                    .setText(R.id.main_express_item_company,item.getCompany());
//            Log.e("rv_adapter","转换完成");
            String sex = item.getSex();
            if(sex.equals("男")){
                myViewHolder.setImageResource(R.id.item_portrait,R.mipmap.male);
            } else {
                myViewHolder.setImageResource(R.id.item_portrait,R.mipmap.female);
            }
            switch (item.getStatus()){
                case "0":
                    myViewHolder.setText(R.id.main_express_item_token_status,"等待接单中");
                    break;
                default:
                    myViewHolder.setText(R.id.main_express_item_token_status,"已完成");
                    break;
//                case "2":
//                    myViewHolder.setText(R.id.main_express_item_token_status,"已完成");
//                    break;
            }
            if(item.getPrice().equals("0")){
                myViewHolder.itemView.findViewById(R.id.main_express_item_status).setVisibility(View.GONE);
            }else{
                myViewHolder.itemView.findViewById(R.id.main_express_item_status).setVisibility(View.VISIBLE);
            }
        }else{
            View view = holder.itemView.findViewById(R.id.footer_load_more);
            loadMoreListener.setLoad(view);
        }
    }
}
