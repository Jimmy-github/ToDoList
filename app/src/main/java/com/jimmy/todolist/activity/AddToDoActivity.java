package com.jimmy.todolist.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jimmy.todolist.R;
import com.jimmy.todolist.model.ToDoModel;
import com.jimmy.todolist.utils.DataService;
import com.jimmy.todolist.widget.DataAndTimePickerDialog;

import java.util.Calendar;

import static android.R.attr.x;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class AddToDoActivity extends AppCompatActivity {
    Toolbar mTbToolBar;

    RelativeLayout mRlTimePicker;

    TextView mTvPickedTime;

    String pickedTime;

    EditText mEtTodoName;

    CheckBox mCbNeedNotify;

    String groupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_to_do);
        groupName = getIntent().getStringExtra("groupName");


        mTbToolBar = (Toolbar) findViewById(R.id.tb_toolbar);
        mTbToolBar.setTitle("添加任务");
//        mTbToolBar.setSubtitle("subtitle");
//        mTbToolBar.setLogo(R.mipmap.ic_launcher);
        mTbToolBar.setNavigationIcon(R.mipmap.back);

        setSupportActionBar(mTbToolBar);

        mRlTimePicker = (RelativeLayout) findViewById(R.id.rl_time_picker);

        mRlTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDateAndTime();


            }
        });


        mTvPickedTime = (TextView) findViewById(R.id.tv_picked_time);

        mEtTodoName = (EditText) findViewById(R.id.et_todo_name);

        mCbNeedNotify = (CheckBox) findViewById(R.id.cb_need_notify);


    }

    private void pickDateAndTime() {
        DataAndTimePickerDialog.showDataAndTimePickerDialog(AddToDoActivity.this, new DataAndTimePickerDialog.OnDataAndTimePickerListener() {
            @Override
            public void onDataAndTimePicker(String time) {
                pickedTime = time;
                mTvPickedTime.setText(time);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemid = item.getItemId();

        switch (itemid) {
            case R.id.item_confirm:
                saveToDo();

                finish();

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void saveToDo() {
        // pickedTime
        String toDoName = mEtTodoName.getText().toString().trim();
        if (toDoName == null || "".equals(toDoName)) {
            Toast.makeText(AddToDoActivity.this, "任务名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isNotify = mCbNeedNotify.isChecked();

        ToDoModel toDoModel = new ToDoModel(System.currentTimeMillis(), isNotify
                , pickedTime, toDoName);


        DataService.saveToDo(AddToDoActivity.this, groupName, toDoModel);

    }
}
