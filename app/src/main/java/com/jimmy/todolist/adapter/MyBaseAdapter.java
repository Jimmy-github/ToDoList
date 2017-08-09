package com.jimmy.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jimmy.todolist.R;

import java.util.List;

/**
 * Created by zhangtianjie on 2017/8/8.
 */

public abstract class MyBaseAdapter<T, E> extends BaseAdapter {


    Context mContext;

    List<T> data;

    public MyBaseAdapter(Context mContext, List<T> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        if (data == null)
            return 0;

        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        E e;
        if (convertView == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(getLayoutId(), parent, false);


            e = getViewHolder(convertView);

            convertView.setTag(e);

        } else {
            e = (E) convertView.getTag();
        }


        afterGetViewHolder(position, e);
        return convertView;
    }

    protected abstract int getLayoutId();

    abstract E getViewHolder(View convertView);

    abstract void afterGetViewHolder(int position, E e);
}
