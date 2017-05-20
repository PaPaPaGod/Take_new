package com.price.take_new.utils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.takeretrofit.bean.myhelpdeliveryorder.MyHelpOrderDatum;
import com.example.takeretrofit.bean.otheruserinfo.OtherUserInfoData;
import com.example.takeretrofit.model.GetOtherUserInfo;
import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.App;
import com.price.take_new.R;
import com.price.take_new.model.GetOtherUserInfoModel;
import com.price.take_new.presenter.GetOtherUserInfoPresenter;
import com.price.take_new.service.api.OnGetOtherUserInfoListener;
import com.price.take_new.service.modelService.IGetOtherUserInfo;
import com.price.take_new.service.viewService.IGetOtherUserInfoView;
import com.price.take_new.utils.item.PersonalExpressItem;

import java.util.List;

/**
 * 代拿快递
 * Created by Administrator on 2016/8/7.
 */
public class PackageHelpAdapter<T> extends CommonRecyclerViewAdapter {

//    private GetOtherUserInfoPresenter presenter;
    private IGetOtherUserInfo model;

    public PackageHelpAdapter(final Context mContext, List<T> mDatas, int layoutId, String token) {
        super(mContext, mDatas, layoutId, token);
//        presenter = new GetOtherUserInfoPresenter(new IGetOtherUserInfoView() {
//            @Override
//            public void onBind(OtherUserInfoData info) {
//
//            }
//
//            @Override
//            public void showToast(String msg) {
//                Toast.makeText(App.getContext(),"fail",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, Object o) {
        final CommonViewHolder myViewHolder = (CommonViewHolder) holder;
        MyHelpOrderDatum item = (MyHelpOrderDatum) o;
//        presenter.getUserInfo(
//                ManagerData.getCachedToken(App.getContext()),item.getUserId());
        if(o!=null){
            model = new GetOtherUserInfoModel();
            model.getOtherInfo(ManagerData.getCachedToken(App.getContext()), item.getUserId(), new OnGetOtherUserInfoListener() {
                @Override
                public void onSuccess(OtherUserInfoData info) {
                    String sex = info.getSex();
                    switch (sex){
                        case "男":
                            myViewHolder.setImageResource(R.id.help_express_portrait,R.mipmap.male);
                            break;
                        case "女":
                            myViewHolder.setImageResource(R.id.help_express_portrait,R.mipmap.female);
                            break;
                        default:
                            myViewHolder.setImageResource(R.id.help_express_portrait,R.mipmap.male);
                            break;
                    }

                }

                @Override
                public void onMsg(String msg) {
                    Log.e("helpAdapter",msg);
                }

                @Override
                public void onError(String msg) {
                    Log.e("helpAdapter-error",msg);
                }
            });
            myViewHolder.setText(R.id.help_express_name,item.getName()).
                    setText(R.id.help_express_time,item.getTakeTime()).
                    setText(R.id.help_express_company,item.getCompany()).
                    setText(R.id.help_express_place,item.getAddress())
                    .setText(R.id.help_express_description,item.getDes());
            Log.e("getName",item.getName());
            //我的快递
                //我代拿的快递
//                myViewHolder.setImage(R.id.help_express_portrait, R.mipmap.male,true,item);
            if (item.getPrice().equals("0")){
                myViewHolder.setText(R.id.tv_express_state,"");
            }
            else {
                myViewHolder.setText(R.id.tv_express_state,"有赏金");
            }
        }else{
            View view = holder.itemView.findViewById(R.id.footer_load_more);
            loadMoreListener.setLoad(view);
        }

    }
}
