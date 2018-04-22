package com.price.take_new.utils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.takeretrofit.bean.mydeliveryorder.MyDeliveryOrderDatum;
import com.example.takeretrofit.bean.myhelpdeliveryorder.MyHelpOrderDatum;
import com.example.takeretrofit.bean.otheruserinfo.OtherUserInfoData;
import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.App;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.model.GetOtherUserInfoModel;
import com.price.take_new.presenter.GetOtherUserInfoPresenter;
import com.price.take_new.service.api.OnGetOtherUserInfoListener;
import com.price.take_new.service.modelService.IGetOtherUserInfo;

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
        CommonViewHolder myViewHolder = (CommonViewHolder) holder;
        MyHelpOrderDatum item = (MyHelpOrderDatum) o;
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
