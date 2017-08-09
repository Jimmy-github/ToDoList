package com.jimmy.todolist.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jimmy.todolist.R;
import com.jimmy.todolist.adapter.GroupAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {

    Toolbar mTbToolBar;
    ListView mLvGroups;
    List<Map.Entry<String, Integer>> grouplist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTbToolBar = (Toolbar) findViewById(R.id.tb_toolbar);
        mTbToolBar.setTitle("分组列表");
//        mTbToolBar.setSubtitle("subtitle");
//        mTbToolBar.setLogo(R.mipmap.ic_launcher);
        mTbToolBar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(mTbToolBar);

        mLvGroups = (ListView) findViewById(R.id.lv_groups);

        mLvGroups.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ToDoListActivity.class);
                String groupName = grouplist.get(position).getKey();

                intent.putExtra("groupName", groupName);

                startActivity(intent);


            }
        });

    }

    @Override
    protected void onResume() {
        SharedPreferences mSharedPreferences = getSharedPreferences("groups", MODE_PRIVATE);

        Map<String, Integer> map = (Map<String, Integer>) mSharedPreferences.getAll();

        Set<Map.Entry<String, Integer>> set = map.entrySet();

        grouplist = new ArrayList<Map.Entry<String, Integer>>(set);

        mLvGroups.setAdapter(new GroupAdapter(MainActivity.this, grouplist));
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemid = item.getItemId();

        switch (itemid) {
            case R.id.item_add:
                Intent intent = new Intent(MainActivity.this, AddGroupActivity.class);

                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
