package com.jimmy.todolist.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.jimmy.todolist.model.ToDoModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zhangtianjie on 2017/8/9.
 */

public class DataService {
    public static List<ToDoModel> getToDoList(Context context, String groupName) {

        SharedPreferences mSharedPreferences = context.getSharedPreferences(groupName, Context.MODE_PRIVATE);
        Map<String, String> all = (Map<String, String>) mSharedPreferences.getAll();
        Set<Map.Entry<String, String>> entrys = all.entrySet();


        List<ToDoModel> toDoModels = new ArrayList<>();

        for (Map.Entry<String, String> entry : entrys) {

            toDoModels.add(new ToDoModel(entry));
        }


        Collections.sort(toDoModels, new Comparator<ToDoModel>() {
            @Override
            public int compare(ToDoModel o1, ToDoModel o2) {
                long iTime = o1.getCreateTime();
                long jTime = o2.getCreateTime();

                return (int) (iTime - jTime);
            }
        });

        return toDoModels;
    }

    public  static  void saveToDo(Context context,String groupName,ToDoModel toDoModel ){
        SharedPreferences mSharedPreferences=context.getSharedPreferences(groupName,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=mSharedPreferences.edit();
        editor.putString(toDoModel.getName(),toDoModel.toString());

        editor.commit();
    }

}
