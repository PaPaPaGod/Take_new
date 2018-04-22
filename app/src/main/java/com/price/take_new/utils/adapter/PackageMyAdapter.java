package com.price.take_new.utils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.takeretrofit.bean.mydeliveryorder.MyDeliveryOrderDatum;
import com.price.take_new.Constant;
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
//        Log.e("PackageMyAdapter status",item.getStatus());
        if(o!=null){
            myViewHolder.setText(R.id.item_personal_express_company,item.getCompany()).
                    setText(R.id.item_personal_express_addr,item.getAddress())
                    .setText(R.id.item_personal_express_desc,item.getDes());
            myViewHolder.setVisibility(R.id.item_personal_express_name, View.GONE);
            switch (item.getFrom_weixin()){
                case 0:
                    myViewHolder.setText(R.id.item_personal_express_weixin, Constant.STRING_CLIENT);
                    break;
                case 1:
                    myViewHolder.setText(R.id.item_personal_express_weixin,Constant.STRING_WEIXIN);
                    break;
            }
        }else{
            View view = holder.itemView.findViewById(R.id.footer_load_more);
            loadMoreListener.setLoad(view);
        }
    }
}
