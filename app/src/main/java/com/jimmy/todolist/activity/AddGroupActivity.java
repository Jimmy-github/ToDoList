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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jimmy.todolist.R;

public class AddGroupActivity extends AppCompatActivity {
    Toolbar mTbToolBar;
    ImageView mIvChosenIcon;
    int choseIconId = -1;
    EditText mEtGroupName;

    public static final int REQUESTCODE = 1000;
    RelativeLayout mRlIconChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        mTbToolBar = (Toolbar) findViewById(R.id.tb_toolbar);
        mTbToolBar.setTitle("添加分组");
//        mTbToolBar.setSubtitle("subtitle");
//        mTbToolBar.setLogo(R.mipmap.ic_launcher);
        mTbToolBar.setNavigationIcon(R.mipmap.back);

        setSupportActionBar(mTbToolBar);


        mIvChosenIcon = (ImageView) findViewById(R.id.iv_chosen_icon);
        mEtGroupName = (EditText) findViewById(R.id.et_group_name);
        mRlIconChoose = (RelativeLayout) findViewById(R.id.rl_icon_choose);
        mRlIconChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddGroupActivity.this, IconChooseActivity.class);


                startActivityForResult(intent, REQUESTCODE);


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUESTCODE && resultCode == RESULT_OK) {
            choseIconId = data.getIntExtra("choosenIcon", -1);
            String chosenIconName = data.getStringExtra("choosenName");

            //  Toast.makeText(AddGroupActivity.this,chosenIconId+"~"+chosenIconName,Toast.LENGTH_SHORT).show();
            mIvChosenIcon.setImageResource(choseIconId);

        }
        super.onActivityResult(requestCode, resultCode, data);
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


                String groupName = mEtGroupName.getText().toString().trim();

                if (groupName == null || "".equals(groupName)) {

                    Toast.makeText(AddGroupActivity.this, "请输入分组名", Toast.LENGTH_SHORT).show();

                    return true;
                }

                if (choseIconId == -1) {
                    Toast.makeText(AddGroupActivity.this, "请选择图标", Toast.LENGTH_SHORT).show();
                    return true;
                }


                saveGroup(groupName);
                finish();

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void saveGroup(String groupName) {
        //1.文件名字 2.模式（私有的，还是其他app共分享）

        SharedPreferences mSharedPreferences = getSharedPreferences("groups", MODE_PRIVATE);

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        //放入缓存区
        editor.putInt(groupName, choseIconId);
        //写入文件
        editor.commit();


    }
}
