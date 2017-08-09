package com.jimmy.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jimmy.todolist.R;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangtianjie on 2017/7/26.
 */

public class GroupAdapter extends MyBaseAdapter<Map.Entry<String, Integer>, GroupAdapter.ViewHolder> {


    public GroupAdapter(Context mContext, List<Map.Entry<String, Integer>> data) {
        super(mContext, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_icon_choose;
    }

    @Override
    ViewHolder getViewHolder(View convertView) {
        ViewHolder mViewHolder = new ViewHolder();


        mViewHolder.mTvName = (TextView) convertView.findViewById(R.id.tv_name);
        mViewHolder.mIvIcon = (ImageView) convertView.findViewById(R.id.iv_icon);


        return mViewHolder;
    }


    @Override
    void afterGetViewHolder(int position, ViewHolder viewHolder) {
        viewHolder.mIvIcon.setImageResource(data.get(position).getValue());
        viewHolder.mTvName.setText(data.get(position).getKey());
    }


//    //这里的getview  用了性能优化
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        //convertView用来做性能优化 做缓存的
//
//        ViewHolder mViewHolder;
//
//        if (convertView == null) {
//            mViewHolder = new ViewHolder();
//
//            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
//
//            //false 代表不将view 加载到parent 父控件中
//            convertView = mLayoutInflater.inflate(R.layout.item_icon_choose, parent, false);
//
//            mViewHolder.mTvName = (TextView) convertView.findViewById(R.id.tv_name);
//            mViewHolder.mIvIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
//
//
//            convertView.setTag(mViewHolder);
//
//        } else {
//            mViewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        mViewHolder.mIvIcon.setImageResource(data.get(position).getValue());
//        mViewHolder.mTvName.setText(data.get(position).getKey());
//        return convertView;
//    }


    class ViewHolder {
        ImageView mIvIcon;
        TextView mTvName;

    }
}
