package com.jimmy.todolist.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.jimmy.todolist.R;
import com.jimmy.todolist.adapter.ToDoAdapter;
import com.jimmy.todolist.model.ToDoModel;
import com.jimmy.todolist.utils.DataService;
import com.jimmy.todolist.utils.ListUtil;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static java.lang.Long.parseLong;

public class ToDoListActivity extends AppCompatActivity {
    Toolbar mTbToolBar;
    RelativeLayout mRlTimePicker;
    String groupName;
    ListView mLvToDos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        mTbToolBar = (Toolbar) findViewById(R.id.tb_toolbar);

        groupName = getIntent().getStringExtra("groupName");


        if (groupName != null) {
            mTbToolBar.setTitle(groupName);
        }


        mTbToolBar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(mTbToolBar);

        mRlTimePicker = (RelativeLayout) findViewById(R.id.rl_time_picker);


        mLvToDos = (ListView) findViewById(R.id.lv_todos);
    }

    @Override
    protected void onResume() {
        super.onResume();




/*        ListUtil.sort(toDos, new ListUtil.Comparator() {
                    @Override
                    public boolean comparator(int i, int j) {


//                        String iValue = toDos.get(i).getValue();
//                        String[] iFields = iValue.split("&");
//                        String iTimeString = iFields[2];
//                        long iTime = Long.parseLong(iTimeString);
//
//
//                        String jValue = toDos.get(j).getValue();
//                        String[] jFields = jValue.split("&");
//                        String jTimeString = jFields[2];
//                        long jTime = Long.parseLong(jTimeString);

//                        return iTime > jTime;
                        long iTime = new ToDoModel(toDos.get(i)).getCreateTime();
                        long jTime = new ToDoModel(toDos.get(j)).getCreateTime();

                        //  return  (long)new ToDoModel(toDos.get(i)).getCreateTime()-(long)new ToDoModel(toDos.get(j)).getCreateTime();

                        return iTime > jTime;
                    }
                }
        );*/

        List<ToDoModel> toDoModels = DataService.getToDoList(ToDoListActivity.this
                , groupName);
        mLvToDos.setAdapter(new ToDoAdapter(ToDoListActivity.this, toDoModels));


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

                Intent intent = new Intent(ToDoListActivity.this, AddToDoActivity.class);

                intent.putExtra("groupName", groupName);
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }


}
