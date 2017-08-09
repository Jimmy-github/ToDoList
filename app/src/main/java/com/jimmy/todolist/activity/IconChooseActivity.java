package com.jimmy.todolist.activity;

import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jimmy.todolist.R;
import com.jimmy.todolist.adapter.IconsAdapter;

import java.util.ArrayList;
import java.util.List;

public class IconChooseActivity extends AppCompatActivity {
    Toolbar mTbToolBar;
    ListView mLvIcons;

    List<Pair<Integer, String>> icons=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_choose);

        mTbToolBar = (Toolbar) findViewById(R.id.tb_toolbar);
        mTbToolBar.setTitle("选择icon");

        initData();

        mLvIcons = (ListView) findViewById(R.id.lv_icons);

        //为listview添加一个适配器adapter
        mLvIcons.setAdapter(new IconsAdapter(IconChooseActivity.this, icons));

        mLvIcons.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //parent 是指listview 本身 view指item id 是getItemID()返回的值

                if (icons!=null) {
                    Intent intent = new Intent();
                    intent.putExtra("choosenIcon", icons.get(position).first);
                    intent.putExtra("choosenName", icons.get(position).second);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });



    }

    private void initData() {
        icons = new ArrayList<>();
        icons.add(new Pair<Integer, String>(R.drawable.icon_account, "account"));
        icons.add(new Pair<Integer, String>(R.drawable.icon_message, "message"));
        icons.add(new Pair<Integer, String>(R.drawable.icon_phone, "phone"));
        icons.add(new Pair<Integer, String>(R.drawable.icon_school, "school"));
        icons.add(new Pair<Integer, String>(R.drawable.icon_setting, "setting"));
        icons.add(new Pair<Integer, String>(R.drawable.icon_submit, "submit"));
        icons.add(new Pair<Integer, String>(R.drawable.icon_collect, "collect"));
    }
}
