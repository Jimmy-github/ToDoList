package com.jimmy.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jimmy.todolist.R;
import com.jimmy.todolist.model.ToDoModel;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangtianjie on 2017/8/8.
 */


public class ToDoAdapter extends MyBaseAdapter<ToDoModel, ToDoAdapter.ViewHolder> {


    public ToDoAdapter(Context mContext, List<ToDoModel> data) {
        super(mContext, data);
    }

    @Override
    protected int getLayoutId() {

        return R.layout.item_to_do_list;
    }

    @Override
    ViewHolder getViewHolder( View convertView) {
        ViewHolder mViewHolder = new ViewHolder();
//        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
//        convertView = mLayoutInflater.inflate(R.layout.item_to_do_list, parent, false);
        mViewHolder.mTv = (TextView) convertView.findViewById(R.id.tv);
        return mViewHolder;
    }

    @Override
    void afterGetViewHolder(int position, ViewHolder viewHolder) {
        viewHolder.mTv.setText(data.get(position).getName()+"");
    }


    class ViewHolder {

        TextView mTv;


    }
}
