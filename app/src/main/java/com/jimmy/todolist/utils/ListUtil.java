package com.jimmy.todolist.utils;

import java.util.List;
import java.util.Map;

import static android.R.id.list;

/**
 * Created by zhangtianjie on 2017/8/9.
 */

public class ListUtil {

    public interface Comparator {
        boolean comparator(int i, int j);
    }



    public  static  <T>  void sort(List<T> list, Comparator mComparator) {
        //冒泡排序算法
        for (int i = 0; i < list.size(); i++) {


            for (int j = i; j < list.size(); j++) {

                if (mComparator.comparator(i, j)) {
                    T t = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, t);


                }


            }


        }


    }
}
