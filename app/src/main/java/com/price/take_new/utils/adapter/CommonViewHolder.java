package com.price.take_new.utils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.price.take_new.utils.listener.FriendClickListener;


/**
 * Created by Administrator on 2016/8/14.
 */
public class CommonViewHolder extends RecyclerView.ViewHolder{

    private SparseArray<View> sparseArray;
    private View convertView;
//    private int position;
    private Context context;
    private String token;

    // 构造方法，完成传统Adapter里的创建convertView对象
    public CommonViewHolder(Context context, View convertView, int layoutId,
                            ViewGroup parent, String token) {
        super(convertView);
//        this.position = position;
        this.sparseArray = new SparseArray<View>();
        this.convertView = convertView;
        this.convertView.setTag(this);
        this.context = context;
        this.token = token;
    }

    // 入口方法，完成传统Adapter里面实例化ViewHolder对象工作
    public static CommonViewHolder getCommonViewHolder(
            Context context, int layoutId,
            ViewGroup parent, String token) {

        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        CommonViewHolder holder = new CommonViewHolder(context, itemView, layoutId, parent,token);
        return holder;
    }

    //根据控件Id获取对应View对象
    public <T extends View> T getView(int viewId) {
        View view = sparseArray.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            sparseArray.put(viewId, view);
        }
        return (T) view;
    }

    //用于返回设置好的ConvertView对象
//    public View getView(){
//        return convertView;
//    }

    //设置TextView 文本
    public CommonViewHolder setText(int viewId, String text){
        TextView tv = getView(viewId);
//        Log.e("viewId",viewId+"--"+text);
        tv.setText(text);
//        Log.e("viewText",tv.getText().toString());
        return this;
    }

    public CommonViewHolder setVisibility(int viewId,int status){
        View view = getView(viewId);
        view.setVisibility(status);
        return this;
    }


    public CommonViewHolder setImageResource(int viewId,int male) {
        ImageView iv = getView(viewId);
        iv.setImageResource(male);
        return this;
    }

    public CommonViewHolder addOnClickListener(int viewId,final FriendClickListener listener){
        ImageButton btn = getView(viewId);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.send();
            }
        });
        return this;
    }
}


