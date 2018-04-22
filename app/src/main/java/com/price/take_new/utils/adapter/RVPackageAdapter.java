package com.price.take_new.utils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.takeretrofit.bean.delivery.DeliveryDatum;
import com.price.take_new.R;

import java.util.List;

/**
 * Created by price on 2016/11/12.
 */

public class RVPackageAdapter<T> extends CommonRecyclerViewAdapter {

    public RVPackageAdapter(Context mContext, List<T> mDatas, int layoutId, String token) {
        super(mContext, mDatas,layoutId,token);
    }

    @Override
    public void convert(final RecyclerView.ViewHolder holder, Object o) {
        CommonViewHolder myViewHolder = (CommonViewHolder) holder;
        if(o!=null){
            DeliveryDatum item = (DeliveryDatum) o;
            myViewHolder.setText(R.id.item_main_express_name,item.getName())
                    .setText(R.id.item_main_express_addr,item.getAddress())
                    .setText(R.id.item_main_express_desc,item.getDes())
                    .setText(R.id.item_main_express_company,item.getCompany());
            String sex = item.getSex();
            if(sex.equals("男")){
                myViewHolder.setImageResource(R.id.item_main_express_portrait,R.drawable.portrait_male);
            } else {
                myViewHolder.setImageResource(R.id.item_main_express_portrait,R.drawable.portrait_female);
            }
//            switch (item.getStatus()){
//                case "0":
//                    myViewHolder.setImageResource(R.id.item_main_express_status,R.drawable.status_done);
//                    break;
//                default:
//                    myViewHolder.setImageResource(R.id.item_main_express_status,R.drawable.status_ongoing);
//                    break;
//            }
            if (item.getIs_friend()){
                myViewHolder.setText(R.id.item_main_express_isfriends,"好友");
            }else {
                myViewHolder.setText(R.id.item_main_express_isfriends,"非好友");
            }
            if(item.getPrice()!=null||item.getSmall_reward()!=null){
                myViewHolder.itemView.findViewById(R.id.item_main_express_reward).setVisibility(View.INVISIBLE);
            }else{
                myViewHolder.itemView.findViewById(R.id.item_main_express_reward).setVisibility(View.VISIBLE);
            }
        }else{
            View view = holder.itemView.findViewById(R.id.footer_load_more);
            loadMoreListener.setLoad(view);
        }
    }
}
